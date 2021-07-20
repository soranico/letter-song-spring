package com.kanozz.beanfactory.dependency;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
class KanoA {

	@Resource(name = "kanoImplB")
//	@Autowired
	private Kano kanoImplB;


	public void setKanoImplB(KanoImplA kanoImplA) {
		this.kanoImplB = kanoImplA;
	}

	public Kano getKano(){
		return kanoImplB;
	}

}
