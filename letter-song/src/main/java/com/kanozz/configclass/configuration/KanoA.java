package com.kanozz.configclass.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	KanoA() {
		log.info("KanoA create");
	}
}
