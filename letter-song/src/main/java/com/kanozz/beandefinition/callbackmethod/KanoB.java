package com.kanozz.beandefinition.callbackmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

class KanoB implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(KanoB.class);
	public void manualInit(){
		log.info("manualInit");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("InitializingBean - afterPropertiesSet");
	}
}
