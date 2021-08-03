package com.kanozz.beandefinition.factorymethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class KanoB {


	KanoB(){
		log.info("KanoB create");
	}

	KanoB createKanoB(){
		log.info("start create kanoB");
		return new KanoB();
	}




}
