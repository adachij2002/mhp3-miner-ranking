package mh.miner.util;

import java.util.Map;

import mh.miner.action.MinerRankingSearchParam;
import mh.miner.manager.ConfigurationManager;

public class QueryParamUtil {
	public static MinerRankingSearchParam parseMinerRankingSearchParam(Map<String, String> params) {
		MinerRankingSearchParam param = new MinerRankingSearchParam();

		param.setKeyword(params.get("q_keyword"));
		try {
			param.setPageIndex(Integer.parseInt(params.get("q_pageIndex")));
		} catch (NumberFormatException e) {
			param.setPageIndex(0);
		}
		try {
			param.setPageSize(Integer.parseInt(params.get("q_pageSize")));
		} catch (NumberFormatException e) {
			param.setPageSize(
					ConfigurationManager.getInstance().getConf().getRankingConf().getMaxPagesize());
		}
		validateMinerRankingSearchParam(param);

		return param;
	}

	public static void validateMinerRankingSearchParam(MinerRankingSearchParam param) {
		if(param.getPageIndex() < 0) {
			param.setPageIndex(0);
		}
		if(param.getPageSize() < 0) {
			param.setPageSize(
					ConfigurationManager.getInstance().getConf().getRankingConf().getMaxPagesize());
		}
	}
}
