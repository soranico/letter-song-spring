package com.kanozz.beandefinition.callbackmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

class KanoC implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(KanoC.class);
	@PostConstruct
	public void init(){
//		log.info("annotation init");
	}

	public void manualInit(){
//		log.info("manualInit");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		log.info("InitializingBean - afterPropertiesSet");
	}
}
