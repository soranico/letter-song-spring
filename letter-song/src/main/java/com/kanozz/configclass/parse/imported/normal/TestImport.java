package com.kanozz.configclass.parse.imported.normal;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Collection;
import java.util.function.Predicate;

public class TestImport {

	/**
	 *
	 * 对于 Import的普通类而言(没有实现Import相关的接口)
	 * 会默认被当成一个配置类去处理,然后递归调用解析这个
	 * 配置类
	 * @see org.springframework.context.annotation.ConfigurationClassParser#processImports(ConfigurationClass, ConfigurationClassParser.SourceClass, Collection, Predicate, boolean)
	 *
	 *
	 */
	@Test
	public void testImportNormal(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(KanoConfiguration.class);
		context.refresh();
		KanoNormal kanoNormal = context.getBean(KanoNormal.class);
		KanoConfiguration kanoConfiguration = context.getBean(KanoConfiguration.class);
	}

}
