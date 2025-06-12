package mh.miner.action;

import mh.miner.manager.ConfigurationManager;
import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.service.MinerRanking;
import mh.miner.service.MinerRankingSearchParam;
import mh.miner.util.PaginationUtil;
import mh.miner.util.QueryParamUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.ArrayDataModel;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.model.DataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class Ranking implements Serializable {

	private SqlSessionFactory sessionFactory;

	private MinerRankingSearchParam minerRankingSearchParam = new MinerRankingSearchParam();
	private DataModel<MinerRanking> minerRankings;
	private int minerCount;
	private int resultCount;
	private int navSize;
	private List<Integer> pages = new ArrayList<Integer>();

	public MinerRankingSearchParam getMinerRankingSearchParam() {
		return minerRankingSearchParam;
	}
	public void setMinerRankingSearchParam(
			MinerRankingSearchParam minerRankingSearchParam) {
		this.minerRankingSearchParam = minerRankingSearchParam;
	}
	public DataModel<MinerRanking> getMinerRankings() {
		return minerRankings;
	}
	public void setMinerRankings(
			DataModel<MinerRanking> minerRankings) {
		this.minerRankings = minerRankings;
	}
	public int getMinerCount() {
		return minerCount;
	}
	public void setMinerCount(int minerCount) {
		this.minerCount = minerCount;
	}
	public int getResultCount() {
		return resultCount;
	}
	public List<Integer> getPages() {
		return pages;
	}

	@PostConstruct
	public void init() {
		sessionFactory = SqlSessionFactoryManager.getInstance().getSqlSessionFactory();

		minerRankingSearchParam.setPageIndex(0);
		minerRankingSearchParam.setPageSize(
				ConfigurationManager.getInstance().getConf().getRankingConf().getMaxPagesize());

		navSize = ConfigurationManager.getInstance().getConf().getRankingConf().getNavsize();

		parseQueryParam();
		searchRanking();
	}

	public String search() {
		minerRankingSearchParam.setPageIndex(0);
		this.searchRanking();

		return "/view/ranking/ranking?faces-redirect=true&includeViewParams=true";
	}

	public String previousPage() {
		minerRankingSearchParam.setPageIndex(
				minerRankingSearchParam.getPageIndex() - 1);
		this.searchRanking();

		return "/view/ranking/ranking?faces-redirect=true&includeViewParams=true";
	}

	public String nextPage() {
		minerRankingSearchParam.setPageIndex(
				minerRankingSearchParam.getPageIndex() + 1);
		this.searchRanking();

		return "/view/ranking/ranking?faces-redirect=true&includeViewParams=true";
	}

	public String movePage() {
		String pagenum = jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("pagenum");
		minerRankingSearchParam.setPageIndex(Integer.parseInt(pagenum));
		this.searchRanking();

		return "/view/ranking/ranking?faces-redirect=true&includeViewParams=true";
	}

	private void parseQueryParam() {
		Map<String, String> params = jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		minerRankingSearchParam = QueryParamUtil.parseMinerRankingSearchParam(params);
	}

	@SuppressWarnings("unchecked")
	private void searchRanking() {
		SqlSession session = sessionFactory.openSession();

		// ranking
		minerCount = (Integer)session.selectOne(
				"mh.miner.entity.TUser.countPublish");
		resultCount = (Integer)session.selectOne(
				"mh.miner.service.MinerRanking.countRanking",
				minerRankingSearchParam);
		List<MinerRanking> rankings = session.selectList(
				"mh.miner.service.MinerRanking.selectRanking",
				minerRankingSearchParam);

		// set DataModel
		if(rankings.size() > 0) {
			minerRankings = new ArrayDataModel<MinerRanking>(
					rankings.toArray(new MinerRanking[rankings.size()]));
		} else {
			minerRankings = null;
		}

		// set Pagination
		pages = PaginationUtil.createPages(
				minerRankingSearchParam.getPageIndex(),
				navSize,
				(int)Math.ceil((double)resultCount / (double)minerRankingSearchParam.getPageSize()));

		session.close();
	}
}
