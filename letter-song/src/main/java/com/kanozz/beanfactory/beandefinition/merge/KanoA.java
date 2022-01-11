package com.kanozz.beanfactory.beandefinition.merge;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Scope(AbstractBeanFactory.SCOPE_PROTOTYPE)
class KanoA {
	private static final Logger log = LoggerFactory.getLogger(KanoA.class);

	KanoA(){
		log.info("kanoA create");
	}

//	@Autowired
//	private KanoB kanoB;


//	public void setKanoB(KanoB kano){
//		this.kanoB = kanoB;
//	}

//	@Override
//	public String toString() {
//		return "KanoA{" +
//				"kanoB=" + kanoB +
//				'}';
//	}
}
