package com.kanozz.beanfactory.metacache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoA {

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
