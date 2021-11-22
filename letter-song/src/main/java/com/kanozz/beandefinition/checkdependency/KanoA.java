package com.kanozz.beandefinition.checkdependency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);

	@Autowired
	private KanoB kanoB;


	private String kanoStr;

	public void setKanoB(KanoB kanoB) {
		this.kanoB = kanoB;
	}

	public void setKanoStr(String kanoStr) {
		this.kanoStr = kanoStr;
	}

	public void setKano(Integer kano){

	}
}
