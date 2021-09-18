package com.kanozz.mvc.zero.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KanoService {
	private static final Logger log = LoggerFactory.getLogger(KanoService.class);
	public KanoService(){
		log.info("service create");
	}
	public void doService(){

	}
}
