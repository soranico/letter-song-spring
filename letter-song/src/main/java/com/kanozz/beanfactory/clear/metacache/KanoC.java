package com.kanozz.beanfactory.clear.metacache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class KanoC {
	private static final Logger log = LoggerFactory.getLogger(KanoC.class);
	KanoC(){
		log.info("KanoC create");
	}

	@Autowired
	private KanoB kanoB;


}
