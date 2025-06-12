package mh.miner.webapi;

import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MiningStatus;
import mh.miner.service.MiningStatusSearchParam;
import mh.miner.util.QueryParamUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.List;


@jakarta.ws.rs.Path("/status")
public class MiningStatusResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@jakarta.ws.rs.GET
	@jakarta.ws.rs.Path("/search")
    @jakarta.ws.rs.Produces("application/json; charset=UTF-8")
	public List<MiningStatus> searchStatus(
			@jakarta.ws.rs.BeanParam
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

	@jakarta.ws.rs.GET
	@jakarta.ws.rs.Path("/count")
    @jakarta.ws.rs.Produces("application/json; charset=UTF-8")
	public int countStatus(
			@jakarta.ws.rs.BeanParam
			MiningStatusSearchParam miningStatusSearchParam) {
		SqlSession session = sessionFactory.openSession();

		int resultCount = (Integer)session.selectOne(
				"mh.miner.service.MiningStatus.countStatus",
				miningStatusSearchParam);

		session.close();

		return resultCount;
	}
}
