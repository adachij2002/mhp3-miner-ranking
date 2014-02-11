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
import mh.miner.util.QueryParamUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sun.jersey.api.core.ResourceContext;


@Path("/miner")
public class MinerResource {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

	@GET
	@Path("/count")
    @Produces("application/json; charset=UTF-8")
	public int countMiner() {
		SqlSession session = sessionFactory.openSession();

		@SuppressWarnings("unchecked")
		int minerCount = (Integer)session.selectOne(
				"mh.miner.entity.TUser.countPublish");

		session.close();

		return minerCount;
	}
}
