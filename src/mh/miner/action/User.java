package mh.miner.action;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mh.miner.entity.TStatus;
import mh.miner.entity.TUser;
import mh.miner.manager.AccountManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@ManagedBean
@ViewScoped
public class User implements Serializable {

	private SqlSessionFactory sessionFactory;

	private TUser tUser = new TUser();

	public TUser gettUser() {
		return tUser;
	}

	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}

	@PostConstruct
	public void init() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);

			Object accountManager =
				FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getSessionMap().get("accountManager");
			if(accountManager instanceof AccountManager) {
				TUser user = ((AccountManager)accountManager).getLoginUser();
				if(user != null) {
					tUser = user;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public String show() {
		return "/view/config/user";
	}

	public String cancel() {
		return "/view/top/main";
	}

	public String create() {
		SqlSession session = null;

		try {
			session = sessionFactory.openSession();
	
			TUser user = (TUser)session.selectOne(
					"mh.miner.entity.TUser.selectById",
					tUser.getId());
	
			if(user != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(
							ResourceBundle.getBundle("messages",
								FacesContext.getCurrentInstance().getViewRoot().getLocale())
									.getString("msg.config.user.alreadyexists")));
				return "";
			}

			tUser.setPublish(false);
			session.insert("mh.miner.entity.TUser.insert", tUser);

			TStatus status = new TStatus();
			status.setChecked(false);
			status.setUserId(tUser.getId());
			session.insert("mh.miner.entity.TStatus.insertForNewUser", status);

			session.commit();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(
						ResourceBundle.getBundle("messages",
							FacesContext.getCurrentInstance().getViewRoot().getLocale())
								.getString("msg.common.addfailure")));
			return "";
		} finally {
			if(session != null) {
				session.close();
			}
		}

		return "/view/top/login";
	}

	public String update() {
		SqlSession session = null;

		try {
			session = sessionFactory.openSession();

			TUser user = (TUser)session.selectOne(
					"mh.miner.entity.TUser.selectById",
					tUser.getId());

			user.setMhName(tUser.getMhName());
			user.setPassword(tUser.getPassword());
			user.setPublish(tUser.isPublish());

			session.update(
					"mh.miner.entity.TUser.update",
					tUser);

			session.commit();

			Object accountManager =
				FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getSessionMap().get("accountManager");
			if(accountManager instanceof AccountManager) {
				((AccountManager)accountManager).setLoginUser(tUser);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(
							ResourceBundle.getBundle("messages",
								FacesContext.getCurrentInstance().getViewRoot().getLocale())
									.getString("msg.common.editfailure")));
			return "";
		} finally {
			if(session != null) {
				session.close();
			}
		}

		return "/view/top/main";
	}

	public String delete() {
		SqlSession session = null;

		try {
			session = sessionFactory.openSession();

			session.delete("mh.miner.entity.TStatus.deleteByUserId", tUser.getId());
			session.delete("mh.miner.entity.TUser.deleteById", tUser.getId());

			session.commit();
			
			Object accountManager =
				FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getSessionMap().get("accountManager");
			if(accountManager instanceof AccountManager) {
				((AccountManager)accountManager).setLoginUser(null);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(
						ResourceBundle.getBundle("messages",
							FacesContext.getCurrentInstance().getViewRoot().getLocale())
								.getString("msg.common.deletefailure")));
			return "";
		} finally {
			if(session != null) {
				session.close();
			}
		}
	
		return "/view/top/main";
	}
}
