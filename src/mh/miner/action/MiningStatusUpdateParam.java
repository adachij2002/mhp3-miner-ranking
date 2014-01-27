package mh.miner.action;

import java.util.List;

public class MiningStatusUpdateParam {

	private List<Long> ids;
	private boolean checked;

	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
