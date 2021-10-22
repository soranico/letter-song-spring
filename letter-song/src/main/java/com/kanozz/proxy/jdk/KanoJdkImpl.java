package com.kanozz.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KanoJdkImpl implements KanoJdk {
	private static final Logger log = LoggerFactory.getLogger(KanoJdkImpl.class);

	@Override
	public void kano01(){
		log.info("kano kano");
	}


	public String  kano02(){
		log.info("kano kano");
		return "kano";
	}

}
