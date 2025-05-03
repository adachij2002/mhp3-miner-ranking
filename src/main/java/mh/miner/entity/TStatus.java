package mh.miner.entity;


public class TStatus {

	private boolean checked;
	private String userId;
	private String amuletId;

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAmuletId() {
		return amuletId;
	}
	public void setAmuletId(String amuletId) {
		this.amuletId = amuletId;
	}
}
