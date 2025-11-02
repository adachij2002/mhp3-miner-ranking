package mh.miner.manager;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class UserAgentManager implements Serializable {

    private static final long serialVersionUID = 1L;

    public boolean isMobile() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String userAgent = request.getHeader("user-agent");
        return userAgent.toLowerCase().contains("android")
                || userAgent.toLowerCase().contains("iphone");
    }
}
