package mh.miner.webapi;

import mh.miner.manager.SqlSessionFactoryManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;


@jakarta.ws.rs.Path("/miner")
public class MinerResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@GET
	@Path("/count")
    @Produces("application/json; charset=UTF-8")
	public int countMiner() {
		SqlSession session = sessionFactory.openSession();

		@SuppressWarnings("unchecked")
		int minerCount = (Integer)session.selectOne(
				"mh.miner.entity.TUser.countPublish");

		session.close();

		return minerCount;
	}
}
