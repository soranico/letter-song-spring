package com.kanozz.configclass.parse.imported.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
class KanoScanImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[0];
	}


	@Override
	public Predicate<String> getExclusionFilter() {
		return ImportSelector.super.getExclusionFilter();
	}


}
