package mh.miner.webapi;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import mh.miner.action.MinerRankingSearchParam;
import mh.miner.entity.MinerRanking;
import mh.miner.manager.ConfigurationManager;
import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.util.QueryParamUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sun.jersey.api.core.ResourceContext;


@Path("/ranking")
public class RankingResource {
	@GET
	@Path("/search")
    @Produces("application/json; charset=UTF-8")
	public List<MinerRanking> getRanking(
			@Context
			ResourceContext rc) {
		SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();

		MinerRankingSearchParam minerRankingSearchParam = rc.getResource(MinerRankingSearchParam.class);
		QueryParamUtil.validateMinerRankingSearchParam(minerRankingSearchParam);

		@SuppressWarnings("unchecked")
		List<MinerRanking> rankings = session.selectList(
				"mh.miner.entity.MinerRanking.selectRanking",
				minerRankingSearchParam);

		session.close();

		return rankings;
	}
}
