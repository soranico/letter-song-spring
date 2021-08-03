package com.kanozz.configclass.parse.inner;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfigurationWithInner {


	/**
	 *
	 * 对于配置类的内部类如果满足配置类条件
	 * 会被当成配置类处理
	 *
	 */
	@Test
	public void testParseInner() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 手动注册一个BD,类型不能为 AnnotationBeanDefinition
		context.register(KanoConfigurationWithInner.class);
		context.refresh();
	}


	@Test
	public void testParseStaticInner() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 手动注册一个BD,类型不能为 AnnotationBeanDefinition
		context.register(KanoConfigurationWithStaticInner.class);
		context.refresh();
	}











}
