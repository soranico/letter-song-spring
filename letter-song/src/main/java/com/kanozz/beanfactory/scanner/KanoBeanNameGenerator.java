package com.kanozz.beanfactory.scanner;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

import java.util.concurrent.atomic.AtomicInteger;

class KanoBeanNameGenerator implements BeanNameGenerator {

	private static final AtomicInteger name = new AtomicInteger(0);

	public static final KanoBeanNameGenerator INSTANCE = new KanoBeanNameGenerator();

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return "kano-"+name.incrementAndGet();
	}
}
