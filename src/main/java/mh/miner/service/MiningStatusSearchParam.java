package mh.miner.service;

import mh.miner.entity.MMine;
import mh.miner.entity.TUser;

import jakarta.ws.rs.QueryParam;
import java.util.List;

public class MiningStatusSearchParam extends Pagination {

	@QueryParam("q_userid")
	private TUser tUser;
	@QueryParam("q_mineid")
	private MMine mMine;
	private List<MiningStatusSortOrder> sortOrders;

	public TUser gettUser() {
		return tUser;
	}
	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}
	public MMine getmMine() {
		return mMine;
	}
	public void setmMine(MMine mMine) {
		this.mMine = mMine;
	}
	public List<MiningStatusSortOrder> getSortOrders() {
		return sortOrders;
	}
	public void setSortOrders(List<MiningStatusSortOrder> sortOrders) {
		this.sortOrders = sortOrders;
	}
}
