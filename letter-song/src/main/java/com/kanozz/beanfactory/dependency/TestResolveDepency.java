package com.kanozz.beanfactory.dependency;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestResolveDepency {
	private static final Logger log = LoggerFactory.getLogger(TestResolveDepency.class);

	/**
	 * 为kanoB注入属性kano时，按照类型查找有两个bean,按照name查找没有
	 * 因此会按照类型查找,找到两个bean注入失败,如果kano改为kanoImplA/kanoImplB
	 * 按照name查找只会有一个,注入成功
	 * <p>
	 * 解决办法
	 * 使用registerResolvableDependency()为kano显示指定注入的属性KanoImplC
	 * <p>
	 * 优先级 @Resource指定的name > registerResolvableDependency()指定 > byName/byType
	 * <p>
	 * 自动注入 byName/byType > @Resource指定的name > registerResolvableDependency()指定
	 * > @Autowire
	 */
	@Test
	public void testRegisterResolvableDependency() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kanozz.beanfactory.dependency");
		context.addBeanFactoryPostProcessor(ChangeDependencyBeanPostProcessor.INSTANCE);
		context.refresh();
		KanoA kanoA = context.getBean(KanoA.class);
		log.error("kano  = {}", kanoA.getKano());
	}


}
