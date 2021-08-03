package com.kanozz.beandefinition.factorymethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class KanoA {




	static KanoB createKanoB(){
		log.info("start create kanoB");
		return new KanoB();
	}

}
