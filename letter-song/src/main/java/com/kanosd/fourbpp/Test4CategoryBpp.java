package com.kanosd.fourbpp;

import com.kanosd.fourbpp.instantiationawarebpp.afterinstantiation.TestPostProcessAfterInstantiation;
import com.kanosd.fourbpp.instantiationawarebpp.beforeintantiation.TestPostProcessBeforeInstantiation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * InstantiationAwareBeanPostProcessor:
 *
 * SmartInstantiationAwareBeanPostProcessor
 *
 * DestructionAwareBeanPostProcessor
 *
 * MergedBeanDefinitionPostProcessor
 *
 */
@Slf4j
public class Test4CategoryBpp {

	private List getBeanList(AnnotationConfigApplicationContext context,Class type){
		String[] beanNamesForType = context.getBeanNamesForType(type);
		List beanList = new ArrayList();
		for (String beanName : beanNamesForType) {
			beanList.add(context.getBean(beanName));
		}
		return beanList;
	}

	/**
	 *
	 * InstantiationBeanPostProcessor在实例化之前和之后(注入属性前)执行回调方法
	 *
	 * 1.
	 * @see InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation(Class, String)
	 * 在对象实例化之前执行,要求返回一个bean即如果此方法返回一个bean,那代表这个bean已经执行完 实例化+初始化
	 * 那么只会执行postProcessAfterInitialization方法
	 * e.g
	 * @see TestPostProcessBeforeInstantiation#testPostProcessBeforeInstantiation()
	 *
	 * 2.
	 * @see InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation(Object, String)
	 * 对象创建完成,没有进行显示或者自动注入属性之前执行,这是一个判断是否需要进行属性填充的操作
	 * e.g
	 * @see TestPostProcessAfterInstantiation#testPostProcessAfterInstantiation()
	 *
	 * 3.
	 *
	 *
	 */
	@Test
	public void testInstantiationAwareBeanPostProcessor(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BppConfig.class);
		List<InstantiationAwareBeanPostProcessor> instantiationAwareBeanPostProcessorList =
				getBeanList(context, InstantiationAwareBeanPostProcessor.class);
		for (InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor : instantiationAwareBeanPostProcessorList) {
			log.info("InstantiationAwareBeanPostProcessor = {}",instantiationAwareBeanPostProcessor);
		}
	}

	@Test
	public void testSmartInstantiationAwareBeanPostProcessor(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BppConfig.class);
		List<SmartInstantiationAwareBeanPostProcessor> smartInstantiationAwareBeanPostProcessorList =
				getBeanList(context, SmartInstantiationAwareBeanPostProcessor.class);

		for (SmartInstantiationAwareBeanPostProcessor smartInstantiationAwareBeanPostProcessor : smartInstantiationAwareBeanPostProcessorList) {
			log.info("SmartInstantiationAwareBeanPostProcessor = {}",smartInstantiationAwareBeanPostProcessor);
		}
	}

	@Test
	public void testDestructionAwareBeanPostProcessor(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BppConfig.class);
		List<DestructionAwareBeanPostProcessor> destructionAwareBeanPostProcessorList =
				getBeanList(context, DestructionAwareBeanPostProcessor.class);
		for (DestructionAwareBeanPostProcessor destructionAwareBeanPostProcessor : destructionAwareBeanPostProcessorList) {

			log.info("DestructionAwareBeanPostProcessor = {}",destructionAwareBeanPostProcessor);
		}
	}


	@Test
	public void testMergedBeanDefinitionPostProcessor(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();
	}
}
