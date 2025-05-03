package mh.miner.webapi;

import com.sun.jersey.api.core.InjectParam;
import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MinerRanking;
import mh.miner.service.MinerRankingSearchParam;
import mh.miner.util.QueryParamUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;


@Path("/ranking")
public class RankingResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@GET
	@Path("/search")
    @Produces("application/json; charset=UTF-8")
	public List<MinerRanking> searchRanking(
			@InjectParam
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
			@InjectParam
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
