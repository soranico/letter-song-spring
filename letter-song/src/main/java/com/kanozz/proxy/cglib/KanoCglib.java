package com.kanozz.proxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public class KanoCglib {
	private static final Logger log = LoggerFactory.getLogger(KanoCglib.class);

	public void kano01(){
		log.info("kano kano");
	}

	public String kano02(){
		log.info("kano kano");
		return "kano";
	}

	public String before(){
		log.info("before");
		return "before";
	}


	public String around(){
		log.info("around");
		return "around";
	}


	public String after(){
		log.info("after");
		return "after";
	}

	public String throwing(){
		log.info("throwing");
		throw new RuntimeException("throw");
	}

	public String afterReturning(){
		log.info("afterReturning");
		return "afterReturning";
	}


}
