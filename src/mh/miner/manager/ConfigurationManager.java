package mh.miner.manager;

import java.io.IOException;
import java.io.InputStream;

import mh.miner.conf.MainConf;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;

public class ConfigurationManager {
	private static final ConfigurationManager instance = new ConfigurationManager();
	private MainConf conf = null;
	private final String filename = "mhp3-miner-ranking-conf.xml";

	private ConfigurationManager() {
		InputStream in = null;
		try {
			Serializer serializer = new Persister(new Format(new HyphenStyle()));
			in = this.getClass().getClassLoader().getResourceAsStream(filename);
			if(in != null) {
				conf = serializer.read(MainConf.class, in, false);
			} else {
				conf = new MainConf();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static ConfigurationManager getInstance() {
		return instance;
	}

	public MainConf getConf() {
		return conf;
	}
}
