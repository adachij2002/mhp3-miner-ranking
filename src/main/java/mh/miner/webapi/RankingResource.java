package mh.miner.webapi;

import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MinerRanking;
import mh.miner.service.MinerRankingSearchParam;
import mh.miner.util.QueryParamUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.List;


@Path("/ranking")
public class RankingResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@GET
	@Path("/search")
    @Produces("application/json; charset=UTF-8")
	public List<MinerRanking> searchRanking(
			@BeanParam
			MinerRankingSearchParam minerRankingSearchParam) {
		SqlSession session = sessionFactory.openSession();

		QueryParamUtil.validatePagination(minerRankingSearchParam);

		@SuppressWarnings("unchecked")
		List<MinerRanking> rankings = session.selectList(
				"mh.miner.service.MinerRanking.selectRanking",
				minerRankingSearchParam);

		session.close();

		return rankings;
	}

	@GET
	@Path("/count")
    @Produces("application/json; charset=UTF-8")
	public int countRanking(
			@BeanParam
			MinerRankingSearchParam minerRankingSearchParam) {
		SqlSession session = sessionFactory.openSession();

		@SuppressWarnings("unchecked")
		int resultCount = (Integer)session.selectOne(
				"mh.miner.service.MinerRanking.countRanking",
				minerRankingSearchParam);

		session.close();

		return resultCount;
	}
}
