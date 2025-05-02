package mh.miner.manager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

@ManagedBean
@SessionScoped
public class UserAgentManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserAgent userAgent;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String userAgentStr = request.getHeader("user-agent");
		userAgent = new UserAgent(userAgentStr);
	}

	public boolean isMobile() {
		return userAgent.getOperatingSystem().isMobileDevice();
	}
}
