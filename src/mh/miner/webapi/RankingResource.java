package mh.miner.webapi;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import mh.miner.action.MinerRankingSearchParam;
import mh.miner.entity.MinerRanking;
import mh.miner.manager.ConfigurationManager;
import mh.miner.manager.SqlSessionFactoryManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Path("/ranking")
public class RankingResource {
	@GET
	@Path("/list")
    @Produces("application/json; charset=UTF-8")
	public List<MinerRanking> getList() {
		SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();

		MinerRankingSearchParam minerRankingSearchParam = new MinerRankingSearchParam();

		minerRankingSearchParam.setPageIndex(0);
		minerRankingSearchParam.setPageSize(
				ConfigurationManager.getInstance().getConf().getMiningConf().getMaxPagesize());

		@SuppressWarnings("unchecked")
		List<MinerRanking> rankings = session.selectList(
				"mh.miner.entity.MinerRanking.selectRanking",
				minerRankingSearchParam);

		session.close();

		return rankings;
	}
}
