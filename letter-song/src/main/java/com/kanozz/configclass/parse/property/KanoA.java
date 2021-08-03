package com.kanozz.configclass.parse.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KanoA {

	@Value("name")
	private String name;

	public void setName(String name) {
		this.name = name;
	}
}
