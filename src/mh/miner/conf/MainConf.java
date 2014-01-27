package mh.miner.conf;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * MainConf provides configuration of main. 
 */
@Root(name="configuration")
public class MainConf {
	@Element(required=false)
	private TopConf topConf = new TopConf(); 
	@Element(required=false)
	private MiningConf miningConf = new MiningConf();

	public TopConf getTopConf() {
		return topConf;
	}
	public void setTopConf(TopConf topConf) {
		this.topConf = topConf;
	}
	public MiningConf getMiningConf() {
		return miningConf;
	}
	public void setMiningConf(MiningConf miningConf) {
		this.miningConf = miningConf;
	} 
}
