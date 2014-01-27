package mh.miner.conf;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * TopConf provides configuration of top page. 
 */
@Root
public class TopConf {
	@Element(required=false)
	private int maxPagesize = 10;
	@Element(required=false)
	private int navsize = 5;

	public int getMaxPagesize() {
		return maxPagesize;
	}
	public int getNavsize() {
		return navsize;
	}
}
