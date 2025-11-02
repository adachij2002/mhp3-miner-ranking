package mh.miner.webapi;

import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MinerRanking;
import mh.miner.service.MinerRankingSearchParam;
import mh.miner.util.QueryParamUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/ranking")
public class RankingResource {
    private final SqlSessionFactory sessionFactory =
            SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

    @GET
    @Path("/search")
    @Produces("application/json; charset=UTF-8")
    public List<MinerRanking> searchRanking(
            @BeanParam MinerRankingSearchParam minerRankingSearchParam) {
        SqlSession session = sessionFactory.openSession();

        QueryParamUtil.validatePagination(minerRankingSearchParam);

        @SuppressWarnings("unchecked")
        List<MinerRanking> rankings =
                session.selectList(
                        "mh.miner.service.MinerRanking.selectRanking", minerRankingSearchParam);

        session.close();

        return rankings;
    }

    @GET
    @Path("/count")
    @Produces("application/json; charset=UTF-8")
    public int countRanking(@BeanParam MinerRankingSearchParam minerRankingSearchParam) {
        SqlSession session = sessionFactory.openSession();

        @SuppressWarnings("unchecked")
        int resultCount =
                session.selectOne(
                        "mh.miner.service.MinerRanking.countRanking", minerRankingSearchParam);

        session.close();

        return resultCount;
    }
}
