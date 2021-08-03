package com.kanozz.configclass.parse.imported.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

class KanoImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{
				"com.kanozz.configclass.parse.imported.selector.KanoA",
				"com.kanozz.configclass.parse.imported.selector.KanoB"
		};
	}


	@Override
	public Predicate<String> getExclusionFilter() {
		return ImportSelector.super.getExclusionFilter();
	}


}
