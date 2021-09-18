package com.kanozz.beandefinition.paternity;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestPaternity {
	private static final Logger log = LoggerFactory.getLogger(TestPaternity.class);
	@Test
	public void testBDPaternity() {
		RootBeanDefinition parent = new RootBeanDefinition(KanoParent.class);
		parent.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);

		// 子BD会继承父BD的属性
		ChildBeanDefinition child = new ChildBeanDefinition("kanoParent");

		child.setBeanClass(KanoChild.class);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBeanDefinition("kanoParent", parent);
		context.registerBeanDefinition("kanoChild", child);

		context.refresh();

	}

	@Test
	public void testSameName() {
		RootBeanDefinition parent = new RootBeanDefinition(KanoParent.class);

		// 子BD会继承父BD的属性
		ChildBeanDefinition child = new ChildBeanDefinition("kano");

		child.setBeanClass(KanoChild.class);

		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
		parentContext.registerBeanDefinition("kano", parent);

		parentContext.refresh();

		AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
		childContext.setParent(parentContext);
		childContext.registerBeanDefinition("kano", child);

		childContext.refresh();

	}
}
