package com.kanozz.beandefinition.callbackmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

class KanoA implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	@Autowired
	private KanoB kanoB;

	@Override
	public void afterPropertiesSet() throws Exception {
//		log.info("InitializingBean - afterPropertiesSet");
	}

	public void initBean(){
//		log.info("initBean callback");
//		log.info("kanoB = {}",kanoB);
	}
}
