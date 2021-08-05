package com.kanozz.beandefinition.callbackmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
class KanoB implements InitializingBean {

	public void manualInit(){
		log.info("manualInit");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("InitializingBean - afterPropertiesSet");
	}
}
