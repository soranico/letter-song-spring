package com.kanosd.beandefinition;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

@Slf4j
public class BeanDefinitionApp {

	@Test
	public void testChildBeanDefinition() {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Parent.class);

		ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition(rootBeanDefinition.getBeanClassName());
		log.info("parent name = {}",childBeanDefinition.getParentName());
	}

}
