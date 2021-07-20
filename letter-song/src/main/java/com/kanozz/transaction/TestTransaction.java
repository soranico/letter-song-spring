package com.kanozz.transaction;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

@Slf4j
public class TestTransaction {

	private static final String CGLIB_CLASS_DIR = "/Users/kano/Desktop/mycode/study/spring-framework-5.3.7/letter-song/src/main/java/com/kanosd/transaction/cglib";

	@Test
	public void testTransaction(){
		// jdk
//		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		// cglib
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,CGLIB_CLASS_DIR);
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(TransactionKanoA.class, TransactionKanoB.class,TransactionKanoC.class);
		context.refresh();
		TransactionKanoA transactionKanoA = context.getBean(TransactionKanoA.class);
		transactionKanoA.testTransactionAB();

	}

	@Test
	public void test() throws ClassNotFoundException {
		Class var1;
		Method[] var10000 = ReflectUtils.findMethods(new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, (var1 = Class.forName("java.lang.Object")).getDeclaredMethods());
	}
}
