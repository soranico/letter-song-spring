package com.kanozz.beandefinition.checkdependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class KanoA {

	@Autowired
	private KanoB kanoB;

	private String kanoStr;

	public void setKanoB(KanoB kanoB) {
		this.kanoB = kanoB;
	}

	public void setKanoStr(String kanoStr) {
		this.kanoStr = kanoStr;
	}
}
