package com.kanosd.fourbpp.instantiationawarebpp.beforeintantiation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
class BeforeBppA implements InitializingBean {

	public BeforeBppA(){
		log.info("newInstance beforeBppA");
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("beforeBppA callback");
	}
}
