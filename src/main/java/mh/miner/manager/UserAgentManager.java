package mh.miner.manager;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@SessionScoped
public class UserAgentManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isMobile() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String userAgent = request.getHeader("user-agent");
        return userAgent.toLowerCase().contains("android")
                || userAgent.toLowerCase().contains("iphone");
    }
}
