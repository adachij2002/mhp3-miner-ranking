package mh.miner.manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CopyrightManager implements Serializable {

    private ResourceBundle msg;
    private String copyrightDate;

    public String getCopyrightDate() {
        return copyrightDate;
    }

    @PostConstruct
    public void init() {
        msg =
                ResourceBundle.getBundle(
                        "messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        String beginDate = msg.getString("label.common.copyright.begindate");
        String nowDate = new SimpleDateFormat("yyyy").format(new Date());
        if (beginDate.compareTo(nowDate) < 0) {
            copyrightDate = beginDate + "-" + nowDate;
        } else {
            copyrightDate = beginDate;
        }
    }
}
