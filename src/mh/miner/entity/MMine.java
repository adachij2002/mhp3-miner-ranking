package mh.miner.entity;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

			session = sessionFactory.openSession();

			@SuppressWarnings("unchecked")
			List<MMine> mineList = session.selectList(
					"mh.miner.entity.MMine.select");

			mines = new LinkedHashMap<String, String>();

			for(MMine mine : mineList) {
				mines.put(mine.getName(), mine.getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	public MMine() {
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
