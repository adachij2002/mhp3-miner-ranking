package mh.miner.conf;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** MiningConf provides configuration of mining page. */
@Root
public class MiningConf {
    @Element(required = false)
    private int maxPagesize = 20;

    @Element(required = false)
    private int navsize = 5;

    public int getMaxPagesize() {
        return maxPagesize;
    }

    public int getNavsize() {
        return navsize;
    }
}
