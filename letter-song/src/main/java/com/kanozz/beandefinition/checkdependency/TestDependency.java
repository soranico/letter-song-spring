package com.kanozz.beandefinition.checkdependency;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDependency {

	/**
	 *
	 * 创建bean需要检查依赖项,只坚持对象
	 * 并且检查属性,用过java内省技术(并非set方法截取)来查找需要
	 * 注入的属性（并非所有属性都需要检查）
	 * 对于KanoA,虽然有两个属性,但只有kanoB需要被检查
	 * @see KanoA
	 *
	 * 即使kanoB被显示表明需要注入,但因为内省不关注属性
	 * 仍然会报错
	 *
	 *
	 */
	@Test
	public void testCheckObject() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoB.class);
		GenericBeanDefinition kanoA = new GenericBeanDefinition();
		kanoA.setBeanClass(KanoA.class);
		// 修改注入模型
		kanoA.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		/** 通过添加属性的方法,需要业务保证单例 */
//		kanoA.getPropertyValues().addPropertyValue("kanoB",new KanoB());
		kanoA.setDependencyCheck(AbstractBeanDefinition.DEPENDENCY_CHECK_OBJECTS);
		context.registerBeanDefinition("kanoA",kanoA);
		context.refresh();
	}

	/**
	 *
	 * 简单检查时会检查对象,字符串,基本类型等
	 * 但不会检查输入流这些复杂对象
	 *
	 */
	@Test
	public void testCheckSimple() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoB.class);
		GenericBeanDefinition kanoC = new GenericBeanDefinition();
		kanoC.setBeanClass(KanoC.class);
		kanoC.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		kanoC.setDependencyCheck(AbstractBeanDefinition.DEPENDENCY_CHECK_SIMPLE);

		MutablePropertyValues propertyValues = kanoC.getPropertyValues();
		propertyValues.addPropertyValue("kanoStr","kano");
		propertyValues.addPropertyValue("kano","18");
		context.registerBeanDefinition("kanoC",kanoC);
		context.refresh();
	}

	@Test
	public void testCheckAll() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoB.class);
		GenericBeanDefinition kanoD = new GenericBeanDefinition();
		kanoD.setBeanClass(KanoD.class);
		kanoD.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		kanoD.setDependencyCheck(AbstractBeanDefinition.DEPENDENCY_CHECK_ALL);

		MutablePropertyValues propertyValues = kanoD.getPropertyValues();
		propertyValues.addPropertyValue("kanoStr","kano");
		propertyValues.addPropertyValue("kano","18");

		propertyValues.addPropertyValue("resource",null);

		context.registerBeanDefinition("kanoD",kanoD);
		context.refresh();
	}


}
