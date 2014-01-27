package mh.miner.manager;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mh.miner.entity.TUser;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@ManagedBean
@SessionScoped
public class AccountManager implements Serializable {

	private ResourceBundle msg;
	private SqlSessionFactory sessionFactory;

	private TUser tUser = new TUser();
	private TUser loginUser;
	private TUser guestUser;

	public TUser gettUser() {
		return tUser;
	}

	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}

	public TUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(TUser loginUser) {
		this.loginUser = loginUser;
	}

	@PostConstruct
	public void init() {
		try {
			msg = ResourceBundle.getBundle("messages",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);

			SqlSession session = sessionFactory.openSession();

			guestUser = (TUser)session.selectOne(
					"mh.miner.entity.TUser.selectById",
					msg.getString("value.common.guest.id"));

			session.close();

			loginUser = guestUser;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String cancel() {
		return "/view/top/main";
	}

	public String login() {
		SqlSession session = sessionFactory.openSession();

		loginUser = (TUser)session.selectOne("mh.miner.entity.TUser.selectById", tUser.getId());

		session.close();

		if(loginUser != null
			&& tUser.getId().equals(loginUser.getId())
			&& tUser.getPassword().equals(loginUser.getPassword())) {
			return "main";
		} else {
			loginUser = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(msg.getString("msg.top.login.failure")));
			return "login";
		}
	}

	public String logout() {
		loginUser = guestUser;
		tUser = new TUser();

		return "main";
	}

	public boolean isLoggedIn() {
		return loginUser != null
			&& loginUser != guestUser;
	}
}
