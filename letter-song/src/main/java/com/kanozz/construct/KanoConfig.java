package com.kanozz.construct;

import org.springframework.context.annotation.Bean;

class KanoConfig {
	@Bean
	public KanoD kanoD(KanoB kanoB){
		return new KanoD(kanoB);
	}
}
