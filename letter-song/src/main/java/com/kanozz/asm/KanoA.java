package com.kanozz.asm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Kano
class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	static {
		log.info("KanoA static init");
	}
}
