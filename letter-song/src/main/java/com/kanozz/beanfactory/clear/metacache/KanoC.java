package com.kanozz.beanfactory.clear.metacache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoC {

	KanoC(){
		log.info("KanoC create");
	}

	@Autowired
	private KanoB kanoB;


}
