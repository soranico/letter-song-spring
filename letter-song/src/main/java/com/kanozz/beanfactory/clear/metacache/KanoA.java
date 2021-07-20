package com.kanozz.beanfactory.clear.metacache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Scope(AbstractBeanFactory.SCOPE_PROTOTYPE)
class KanoA {

	KanoA(){
		log.info("kanoA create");
	}

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
