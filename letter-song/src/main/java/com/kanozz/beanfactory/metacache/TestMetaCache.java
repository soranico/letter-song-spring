package com.kanozz.beanfactory.metacache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class TestMetaCache {


	/**
	 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#getBeanNamesForType(Class, boolean, boolean)}
	 * 第一次A注入B 此时B 没有在allBeanNamesByType缓存中,会去遍历所有BD找到此类型的缓存
	 * "class com.kanozz.beanfactory.metacache.KanoB"(Class) ->  ["kanoB"]
	 *
	 * 第二次C注入B时可以直接从缓存中拿出数据,而不需要再次遍历所有BD
	 */
	@Test
	public void testMataCache() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kanozz.beanfactory.metacache");
		context.refresh();
		KanoA kanoA = context.getBean(KanoA.class);

	}


}
