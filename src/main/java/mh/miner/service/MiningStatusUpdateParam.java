package mh.miner.service;

import java.util.List;

public class MiningStatusUpdateParam {

	private String userId;
	private List<String> amuletIds;
	private boolean checked;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getAmuletIds() {
		return amuletIds;
	}
	public void setAmuletIds(List<String> amuletIds) {
		this.amuletIds = amuletIds;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
