package mh.miner.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import mh.miner.entity.MinerRanking;
import mh.miner.manager.ConfigurationManager;
import mh.miner.manager.SqlSessionFactoryManager;
import mh.miner.util.PaginationUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@ManagedBean
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

		return "/view/ranking/miner?faces-redirect=true&includeViewParams=true";
	}

	public String previousPage() {
		minerRankingSearchParam.setPageIndex(
				minerRankingSearchParam.getPageIndex() - 1);
		this.searchRanking();

		return "/view/ranking/miner?faces-redirect=true&includeViewParams=true";
	}

	public String nextPage() {
		minerRankingSearchParam.setPageIndex(
				minerRankingSearchParam.getPageIndex() + 1);
		this.searchRanking();

		return "/view/ranking/miner?faces-redirect=true&includeViewParams=true";
	}

	public String movePage() {
		String pagenum = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("pagenum");
		minerRankingSearchParam.setPageIndex(Integer.parseInt(pagenum));
		this.searchRanking();

		return "/view/ranking/miner?faces-redirect=true&includeViewParams=true";
	}

	private void parseQueryParam() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		minerRankingSearchParam.setKeyword(params.get("q_keyword"));
		try {
			int index = Integer.parseInt(params.get("q_pageIndex"));
			if(index > 0) {
				minerRankingSearchParam.setPageIndex(index);
			} else {
				minerRankingSearchParam.setPageIndex(0);
			}
		} catch (NumberFormatException e) {
			minerRankingSearchParam.setPageIndex(0);
		}
		try {
			int size = Integer.parseInt(params.get("q_pageSize"));
			if(size > 0) {
				minerRankingSearchParam.setPageSize(size);
			} else {
				minerRankingSearchParam.setPageSize(
						ConfigurationManager.getInstance().getConf().getRankingConf().getMaxPagesize());
			}
		} catch (NumberFormatException e) {
			minerRankingSearchParam.setPageSize(
					ConfigurationManager.getInstance().getConf().getRankingConf().getMaxPagesize());
		}
	}

	@SuppressWarnings("unchecked")
	private void searchRanking() {
		SqlSession session = sessionFactory.openSession();

		// ranking
		minerCount = (Integer)session.selectOne(
				"mh.miner.entity.MinerRanking.countMinerTotal");
		resultCount = (Integer)session.selectOne(
				"mh.miner.entity.MinerRanking.countRanking",
				minerRankingSearchParam);
		List<MinerRanking> rankings = session.selectList(
				"mh.miner.entity.MinerRanking.selectRanking",
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
