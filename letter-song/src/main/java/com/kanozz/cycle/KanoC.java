package com.kanozz.cycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class KanoC {

	private static final Logger log = LoggerFactory.getLogger(KanoC.class);

	public KanoC(KanoD kanoD){
		log.info("kanoD = {}",kanoD);
	}
}
