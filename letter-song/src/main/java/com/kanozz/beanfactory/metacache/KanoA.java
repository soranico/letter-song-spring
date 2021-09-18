package com.kanozz.beanfactory.metacache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	@Autowired
	private KanoB kanoB;


	public void setKanoB(KanoB kano){
		this.kanoB = kanoB;
	}

	@Override
	public String toString() {
		return "KanoA{" +
				"kanoB=" + kanoB +
				'}';
	}
}
