package mh.miner.entity;

import java.io.Serializable;




public class TUser {

	private String id;
	private String comment;
	private String mhName;
	private String password;
	private boolean publish;

    public TUser() {
    }

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMhName() {
		return this.mhName;
	}
	public void setMhName(String mhName) {
		this.mhName = mhName;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isPublish() {
		return publish;
	}
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
}
