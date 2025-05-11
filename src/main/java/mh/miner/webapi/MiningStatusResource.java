package mh.miner.webapi;

import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MiningStatus;
import mh.miner.service.MiningStatusSearchParam;
import mh.miner.util.QueryParamUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;


@Path("/status")
public class MiningStatusResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@GET
	@Path("/search")
    @Produces("application/json; charset=UTF-8")
	public List<MiningStatus> searchStatus(
			@BeanParam
			MiningStatusSearchParam miningStatusSearchParam) {
		SqlSession session = sessionFactory.openSession();

		QueryParamUtil.validatePagination(miningStatusSearchParam);

		@SuppressWarnings("unchecked")
		List<MiningStatus> statuses = session.selectList(
				"mh.miner.service.MiningStatus.selectStatus",
				miningStatusSearchParam);

		session.close();

		return statuses;
	}

	@GET
	@Path("/count")
    @Produces("application/json; charset=UTF-8")
	public int countStatus(
			@BeanParam
			MiningStatusSearchParam miningStatusSearchParam) {
		SqlSession session = sessionFactory.openSession();

		int resultCount = (Integer)session.selectOne(
				"mh.miner.service.MiningStatus.countStatus",
				miningStatusSearchParam);

		session.close();

		return resultCount;
	}
}
