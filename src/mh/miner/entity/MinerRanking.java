package mh.miner.entity;

public class MinerRanking {

	private String userId;
	private String mhName;
	private int amuletCount;
	private String comment;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMhName() {
		return mhName;
	}
	public void setMhName(String mhName) {
		this.mhName = mhName;
	}
	public int getAmuletCount() {
		return amuletCount;
	}
	public void setAmuletCount(int amuletCount) {
		this.amuletCount = amuletCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
