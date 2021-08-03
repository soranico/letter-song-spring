package com.kanozz.configclass.parse.property;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfigurationProperty {


	/**
	 *
	 * 存放所有的配置信息
	 * @see org.springframework.core.env.MutablePropertySources
	 *
	 * 默认第一个为 systemProperties 即系统配置
	 * 第二个为 systemEnvironment
	 *
	 *
	 *
	 */
	@Test
	public void testParseProperty() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfigurationProperty.class);
		context.refresh();
	}

	/**
	 *
	 * 存放单个配置文件,如果没有指定name,会自动生成一个name
	 * @see org.springframework.core.io.support.ResourcePropertySource
	 *
	 * 存放同名的文件,里面存放了多个 ResourcePropertySource
	 * @see org.springframework.core.env.CompositePropertySource
	 * 重名文件新添加的会放在首位
	 *
	 *
	 *
	 */
	@Test
	public void testParseSameNameProperty() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfigurationSameNameProperty.class);
		context.refresh();
	}









}
