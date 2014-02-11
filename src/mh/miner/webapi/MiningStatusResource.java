package mh.miner.webapi;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import mh.miner.manager.ConfigurationManager;
import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MinerRanking;
import mh.miner.service.MinerRankingSearchParam;
import mh.miner.service.MiningStatus;
import mh.miner.service.MiningStatusSearchParam;
import mh.miner.util.QueryParamUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.api.core.ResourceContext;


@Path("/status")
public class MiningStatusResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@GET
	@Path("/search")
    @Produces("application/json; charset=UTF-8")
	public List<MiningStatus> searchStatus(
			@InjectParam
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
			@InjectParam
			MiningStatusSearchParam miningStatusSearchParam) {
		SqlSession session = sessionFactory.openSession();

		int resultCount = (Integer)session.selectOne(
				"mh.miner.service.MiningStatus.countStatus",
				miningStatusSearchParam);

		session.close();

		return resultCount;
	}
}
