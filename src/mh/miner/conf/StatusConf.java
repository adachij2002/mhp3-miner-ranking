package mh.miner.conf;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * StatusConf provides configuration of status page. 
 */
@Root
public class StatusConf {
	@Element(required=false)
	private int maxPagesize = 20;
	@Element(required=false)
	private int navsize = 5;

	public int getMaxPagesize() {
		return maxPagesize;
	}
	public int getNavsize() {
		return navsize;
	}
}
