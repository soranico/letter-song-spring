package com.kanozz.configclass.parse.imported.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
class KanoB implements ImportSelector {
	private static final Logger log = LoggerFactory.getLogger(KanoB.class);
	KanoB(){
		log.info("KanoB create");
	}


	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[0];
	}
}
