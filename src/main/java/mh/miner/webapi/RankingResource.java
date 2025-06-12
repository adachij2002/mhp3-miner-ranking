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


@jakarta.ws.rs.Path("/ranking")
public class RankingResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@jakarta.ws.rs.GET
	@jakarta.ws.rs.Path("/search")
    @jakarta.ws.rs.Produces("application/json; charset=UTF-8")
	public List<MinerRanking> searchRanking(
			@jakarta.ws.rs.BeanParam
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

	@jakarta.ws.rs.GET
	@jakarta.ws.rs.Path("/count")
    @jakarta.ws.rs.Produces("application/json; charset=UTF-8")
	public int countRanking(
			@jakarta.ws.rs.BeanParam
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
