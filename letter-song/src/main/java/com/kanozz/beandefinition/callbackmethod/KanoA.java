package com.kanozz.beandefinition.callbackmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class KanoA implements InitializingBean {
	@Autowired
	private KanoB kanoB;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("InitializingBean - afterPropertiesSet");
	}

	public void initBean(){
		log.info("initBean callback");
		log.info("kanoB = {}",kanoB);
	}
}
