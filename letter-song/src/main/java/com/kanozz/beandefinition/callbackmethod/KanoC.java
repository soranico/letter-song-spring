package com.kanozz.beandefinition.callbackmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

@Slf4j
class KanoC implements InitializingBean {

	@PostConstruct
	public void init(){
		log.info("annotation init");
	}

	public void manualInit(){
		log.info("manualInit");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("InitializingBean - afterPropertiesSet");
	}
}
