package com.kanozz.configclass.parse.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);
	@Value("name")
	private String name;

	public void setName(String name) {
		this.name = name;
	}
}
