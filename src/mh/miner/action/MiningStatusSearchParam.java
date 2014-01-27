package mh.miner.action;

import java.io.Serializable;
import java.util.List;

import mh.miner.entity.MMine;
import mh.miner.entity.TUser;

public class MiningStatusSearchParam extends Pagination {

	private TUser tUser;
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
