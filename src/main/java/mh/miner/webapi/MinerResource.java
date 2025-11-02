package mh.miner.webapi;

import mh.miner.manager.SqlSessionFactoryManager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/miner")
public class MinerResource {
    private final SqlSessionFactory sessionFactory =
            SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

    @GET
    @Path("/count")
    @Produces("application/json; charset=UTF-8")
    public int countMiner() {
        SqlSession session = sessionFactory.openSession();

        @SuppressWarnings("unchecked")
        int minerCount = session.selectOne("mh.miner.entity.TUser.countPublish");

        session.close();

        return minerCount;
    }
}
