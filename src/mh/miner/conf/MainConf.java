package mh.miner.conf;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * MainConf provides configuration of main. 
 */
@Root(name="configuration")
public class MainConf {
	@Element(required=false)
	private RankingConf rankingConf = new RankingConf(); 
	@Element(required=false)
	private StatusConf statusConf = new StatusConf();

	public RankingConf getRankingConf() {
		return rankingConf;
	}
	public void setRankingConf(RankingConf rankingConf) {
		this.rankingConf = rankingConf;
	}
	public StatusConf getStatusConf() {
		return statusConf;
	}
	public void setMiningConf(StatusConf statusConf) {
		this.statusConf = statusConf;
	} 
}
