package mh.miner.manager;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@Named
@SessionScoped
public class CopyrightManager implements Serializable {

	private ResourceBundle msg;
	private String copyrightDate;

	public String getCopyrightDate() {
		return copyrightDate;
	}

	@PostConstruct
	public void init() {
		msg = ResourceBundle.getBundle("messages",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());

		String beginDate = msg.getString("label.common.copyright.begindate");
		String nowDate = new SimpleDateFormat("yyyy").format(new Date());
		if(beginDate.compareTo(nowDate) < 0) {
			copyrightDate = beginDate + "-" + nowDate;   
		} else {
			copyrightDate = beginDate;
		}
	}
}
