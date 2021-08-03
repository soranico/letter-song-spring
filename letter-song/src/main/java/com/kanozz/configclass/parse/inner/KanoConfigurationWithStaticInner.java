package com.kanozz.configclass.parse.inner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KanoConfigurationWithStaticInner {



	private static class KanoStaticConfigurationClass{
		@Bean
		public KanoA kanoA (){
			return new KanoA();
		}
	}

}
