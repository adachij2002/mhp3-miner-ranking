package mh.miner.entity;



public class MAmulet {

	private String id;
	private int seed;
	private String name;
	private int slotNum;
	private String note;
	private String skillId1;
	private String skillId2;

    public MAmulet() {
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeed() {
		return seed;
	}
	public void setSeed(int seed) {
		this.seed = seed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSlotNum() {
		return slotNum;
	}
	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSkillId1() {
		return skillId1;
	}
	public void setSkillId1(String skillId1) {
		this.skillId1 = skillId1;
	}
	public String getSkillId2() {
		return skillId2;
	}
	public void setSkillId2(String skillId2) {
		this.skillId2 = skillId2;
	}
}
