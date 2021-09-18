package com.kanozz.beandefinition.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KanoA {

	private static final Logger log = LoggerFactory.getLogger(KanoA.class);


	static KanoB createKanoB(){
//		log.info("start create kanoB");
		return new KanoB();
	}

}
