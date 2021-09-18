package com.kanozz.fourbpp.instantiationawarebpp.beforeintantiation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

class BeforeBppA implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(BeforeBppA.class);
	public BeforeBppA(){
		log.info("newInstance beforeBppA");
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("beforeBppA callback");
	}
}
