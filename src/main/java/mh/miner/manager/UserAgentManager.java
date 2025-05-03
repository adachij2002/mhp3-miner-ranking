package mh.miner.manager;

import eu.bitwalker.useragentutils.UserAgent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

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
