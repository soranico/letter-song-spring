package com.kanozz.cycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;


class KanoD {

	private static final Logger log = LoggerFactory.getLogger(KanoD.class);

	private KanoC kanoC;
	public KanoD(@Lazy KanoC kanoC){
//		log.info("kanoC = {}",kanoC);
		this.kanoC = kanoC;
	}

	public KanoC getKanoC() {
		return kanoC;
	}
}
