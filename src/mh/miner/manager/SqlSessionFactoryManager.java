package mh.miner.manager;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryManager {
	private static final SqlSessionFactoryManager instance = new SqlSessionFactoryManager();
	private static final String filename = "mybatis-config.xml";
	private SqlSessionFactory sqlSessionFactory = null;

	private SqlSessionFactoryManager() {
		try {
			Reader reader = Resources.getResourceAsReader(filename);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactoryManager getInstance() {
		return instance;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
