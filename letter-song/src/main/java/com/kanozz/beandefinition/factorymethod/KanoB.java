package com.kanozz.beandefinition.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KanoB {
	private static final Logger log = LoggerFactory.getLogger(KanoB.class);

	KanoB(){
//		log.info("KanoB create");
	}

	KanoB createKanoB(){
//		log.info("start create kanoB");
		return new KanoB();
	}




}
