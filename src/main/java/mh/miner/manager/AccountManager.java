package mh.miner.manager;

import mh.miner.entity.TUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

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

	public TUser getGuestUser() {
		return guestUser;
	}

	@PostConstruct
	public void init() {
		msg = ResourceBundle.getBundle("messages",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

		SqlSession session = sessionFactory.openSession();

		guestUser = (TUser)session.selectOne(
				"mh.miner.entity.TUser.selectById",
				msg.getString("value.common.guest.id"));

		session.close();

		loginUser = guestUser.clone();
	}

	public String login() {
		SqlSession session = sessionFactory.openSession();

		loginUser = (TUser)session.selectOne("mh.miner.entity.TUser.selectById", tUser.getId());

		session.close();

		if(loginUser != null
			&& tUser.getId().equals(loginUser.getId())
			&& tUser.getPassword().equals(loginUser.getPassword())) {
			return "/view/ranking/ranking?faces-redirect=true";
		} else {
			loginUser = guestUser;
			tUser = new TUser();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(msg.getString("msg.login.login.failure")));
			return "login";
		}
	}

	public String logout() {
		loginUser = guestUser.clone();
		tUser = new TUser();

		return "/view/ranking/ranking?faces-redirect=true";
	}

	public boolean isLoggedIn() {
		return loginUser != null
			&& !loginUser.getId().equals(guestUser.getId());
	}
}
