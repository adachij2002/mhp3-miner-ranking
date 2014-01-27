package mh.miner.entity;

public class MiningStatus {

	private long id;
	private boolean checked;
	private String amuletSeed;
	private String mineName;
	private String skill1Name;
	private String skill2Name;
	private int skill1Point;
	private int skill2Point;
	private int slotNum;
	private String amuletLevel;
	private String amuletType;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getAmuletSeed() {
		return amuletSeed;
	}
	public void setAmuletSeed(String amuletSeed) {
		this.amuletSeed = amuletSeed;
	}
	public String getMineName() {
		return mineName;
	}
	public void setMineName(String mineName) {
		this.mineName = mineName;
	}
	public String getSkill1Name() {
		return skill1Name;
	}
	public void setSkill1Name(String skill1Name) {
		this.skill1Name = skill1Name;
	}
	public String getSkill2Name() {
		return skill2Name;
	}
	public void setSkill2Name(String skill2Name) {
		this.skill2Name = skill2Name;
	}
	public int getSkill1Point() {
		return skill1Point;
	}
	public void setSkill1Point(int skill1Point) {
		this.skill1Point = skill1Point;
	}
	public int getSkill2Point() {
		return skill2Point;
	}
	public void setSkill2Point(int skill2Point) {
		this.skill2Point = skill2Point;
	}
	public int getSlotNum() {
		return slotNum;
	}
	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}
	public String getAmuletLevel() {
		return amuletLevel;
	}
	public void setAmuletLevel(String amuletLevel) {
		this.amuletLevel = amuletLevel;
	}
	public String getAmuletType() {
		return amuletType;
	}
	public void setAmuletType(String amuletType) {
		this.amuletType = amuletType;
	}
}
