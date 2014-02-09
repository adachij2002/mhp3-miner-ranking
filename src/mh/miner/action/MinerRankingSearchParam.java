package mh.miner.action;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;


public class MinerRankingSearchParam extends Pagination {

	@QueryParam("q_keyword")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
