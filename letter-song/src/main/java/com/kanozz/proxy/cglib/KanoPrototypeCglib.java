package com.kanozz.proxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KanoPrototypeCglib {

	private static final Logger log = LoggerFactory.getLogger(KanoPrototypeCglib.class);

	public void kano01(){
		log.info("kano kano");
	}

	public String  kano02(){
		log.info("kano kano");
		return "kano";
	}

}
