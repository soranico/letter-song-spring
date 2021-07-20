package com.kanozz.fourbpp.instantiationawarebpp.beforeintantiation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
class BeforeBppB implements InitializingBean {

	public BeforeBppB(){
		log.info("newInstance beforeBppB");
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("beforeBppB callback");
	}
}
