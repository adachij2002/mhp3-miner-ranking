package mh.miner.entity;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import mh.miner.manager.SqlSessionFactoryManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


@ManagedBean
@SessionScoped
public class MMine implements Serializable {

	private String id;
	private String name;
	private static Map<String, String> mines;

	static {
		SqlSession session = null;

		try {
			SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

			session = sessionFactory.openSession();

			@SuppressWarnings("unchecked")
			List<MMine> mineList = session.selectList(
					"mh.miner.entity.MMine.select");

			mines = new LinkedHashMap<String, String>();

			for(MMine mine : mineList) {
				mines.put(mine.getName(), mine.getId());
			}
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public MMine() {
    }
	public MMine(String id) {
		this.id = id;
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getMines() {
		return mines;
	}
}
