package mh.miner.conf;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * RankingConf provides configuration of ranking page. 
 */
@Root
public class RankingConf {
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
