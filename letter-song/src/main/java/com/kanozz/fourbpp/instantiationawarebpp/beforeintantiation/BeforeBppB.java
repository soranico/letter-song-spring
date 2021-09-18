package com.kanozz.fourbpp.instantiationawarebpp.beforeintantiation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

class BeforeBppB implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(BeforeBppB.class);
	public BeforeBppB(){
		log.info("newInstance beforeBppB");
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("beforeBppB callback");
	}
}
