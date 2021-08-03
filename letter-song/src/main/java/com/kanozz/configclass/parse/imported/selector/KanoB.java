package com.kanozz.configclass.parse.imported.selector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
@Slf4j
class KanoB implements ImportSelector {

	KanoB(){
		log.info("KanoB create");
	}


	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[0];
	}
}
