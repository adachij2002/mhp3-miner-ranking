package mh.miner.action;

import java.io.Serializable;

import mh.miner.entity.TUser;

public class MinerRankingSearchParam extends Pagination {

	private TUser tUser = new TUser();

	public TUser gettUser() {
		return tUser;
	}
	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}
}
