package com.kanozz.transaction;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;

public class TestTransaction {
	private static final Logger log = LoggerFactory.getLogger(TestTransaction.class);
	private static final String CGLIB_CLASS_DIR = "/Users/kano/Desktop/mycode/study/spring-framework-5.3.7/letter-song/src/main/java/com/kanosd/transaction/cglib";

	public void outJdk() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	}

	public void outCglib() {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CGLIB_CLASS_DIR);
	}

	/**
	 * 非事务方法A() 里调用事务方法 B()
	 * 假设 实例 kano 其生成的代理实例为 kanoProxy
	 *
	 * 调用 A() 时 通过
	 * kanoProxy.A() 此时会调用到 DynamicAdvisedInterceptor 的 intercept()
	 * 此时获取到的拦截器为 空 直接进行方法调用
	 * 此时调用的是 kano.A() 也就是执行的是非代理实例的方法
	 * 在A()中 直接调用 B() 对于 B()而言隐含了 this.B()
	 * 而当前的this 为 kano 而不是代理实例 kanoProxy 因此事务不会生效
	 *
	 * 这个注解 @EnableTransactionManagement 引入了一个配置类
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurationSelector#selectImports(AdviceMode)
	 * 默认会注册两个 bean
	 * 1. 用于引入代理环境的
	 * @see org.springframework.context.annotation.AutoProxyRegistrar
	 * 会注册一个BD到spring容器,如果当前没有显示启用代理
	 * @see org.springframework.context.annotation.AutoProxyRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)
	 * 注册的类为
	 * @see org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator
	 * 如果启用注解代理 @EnableAspectJAutoProxy
	 * 因为 AnnotationAwareAspectJAutoProxyCreator优先级高，因此事务的会被替换
	 * @see org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
	 * 替换
	 * @see org.springframework.aop.config.AopConfigUtils#registerOrEscalateApcAsRequired(Class, BeanDefinitionRegistry, Object)
	 *
	 * 2. 注册事务配置类
	 * @see org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration
	 * 这个配置类会添加一个拦截器bean
	 * @see org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration#transactionInterceptor(TransactionAttributeSource)
	 * 解析 @Transactional 的
	 * @see ProxyTransactionManagementConfiguration#transactionAttributeSource()
	 *
	 * 二. 代理方法最终调用
	 * 首先调用拦截器
	 * @see org.springframework.transaction.interceptor.TransactionInterceptor#invoke(MethodInvocation)
	 * 然后最终调用
	 * @see TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)
	 *
	 * 1. 解析方法的注解信息
	 * @see org.springframework.transaction.annotation.AnnotationTransactionAttributeSource#getTransactionAttribute(Method, Class)
	 *
	 * 2. 获取用户配置的事务管理器
	 * @see TransactionAspectSupport#determineTransactionManager(TransactionAttribute)
	 *
	 * 3. 创建事务信息
	 * @see TransactionAspectSupport#createTransactionIfNecessary(PlatformTransactionManager, TransactionAttribute, String)
	 *
	 * 3.1 获取事务状态
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#getTransaction(TransactionDefinition)
	 *
	 * 首先获取事务 会从线程上下文中获取
	 * @see DataSourceTransactionManager#doGetTransaction()
	 * 返回
	 * @see org.springframework.jdbc.datasource.DataSourceTransactionManager.DataSourceTransactionObject
	 *
	 * 3.1.1 已存在事务
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#isExistingTransaction(Object)
	 * 如果存在那么这个值是从TL里获取的必然不是第一次获取了
	 * @see DataSourceTransactionManager#isExistingTransaction(Object)
	 * 处理已存在事务
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#handleExistingTransaction(TransactionDefinition, Object, boolean)
	 * 当前调用方法不支持事务直接异常
	 *
	 * 当前调用方法不用事务，挂起事务封装线程上下文和事务信息
	 * 不支持事务会设置事务状态为false因为在准备事务的时候传入的是null
	 * @see AbstractPlatformTransactionManager#prepareTransactionStatus(TransactionDefinition, Object, boolean, boolean, boolean, Object)
	 * @see AbstractPlatformTransactionManager#prepareSynchronization(DefaultTransactionStatus, TransactionDefinition)
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager.SuspendedResourcesHolder
	 * 内嵌事务,创建保存点执行
	 * 新事务,创建新事务执行
	 *
	 * 3.1.2 不存在事务需要事务
	 * 创建一个事务
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#startTransaction(TransactionDefinition, Object, boolean, AbstractPlatformTransactionManager.SuspendedResourcesHolder)
	 * 是否真正开启事务
	 * @see AbstractPlatformTransactionManager#newTransactionStatus(TransactionDefinition, Object, boolean, boolean, boolean, Object)
	 * 只有线程上下文没有数据才标记这个是真正一个新事务
	 * @see TransactionSynchronizationManager#isSynchronizationActive()
	 *
	 * 而这个值的设置是在从数据源连接池获取连接后再设置的
	 * @see AbstractPlatformTransactionManager#prepareTransactionStatus(TransactionDefinition, Object, boolean, boolean, boolean, Object)
	 *
	 * 从数据源连接池获取连接
	 * @see DataSourceTransactionManager#doBegin(Object, TransactionDefinition)
	 * 只有 ConnectionHolder 为空 也就是当前线程上下文没有事务信息的时候才会获取
	 * @see org.springframework.jdbc.datasource.DataSourceTransactionManager.DataSourceTransactionObject
	 *
	 * 只读事务会首先执行
	 * SET TRANSACTION READ ONLY
	 * 绑定事务到线程上下文
	 * @see TransactionSynchronizationManager#bindResource(Object, Object)
	 *
	 * 3.2 准备事务信息
	 * 上一步返回的
	 * @see org.springframework.transaction.TransactionStatus
	 * 会被封装为
	 * @see org.springframework.transaction.interceptor.TransactionAspectSupport.TransactionInfo
	 * 并且放到线程上下文中
	 * @see TransactionAspectSupport#transactionInfoHolder
	 * 
	 *
	 * 三,异常回滚
	 * @see TransactionAspectSupport#completeTransactionAfterThrowing(TransactionAspectSupport.TransactionInfo, Throwable) 
	 * 1. 判断异常是否支持回滚
	 * @see org.springframework.transaction.interceptor.RuleBasedTransactionAttribute#rollbackOn(Throwable) 
	 * 
	 * 2. 处理回滚
	 * @see AbstractPlatformTransactionManager#rollback(TransactionStatus)
	 * @see AbstractPlatformTransactionManager#processRollback(DefaultTransactionStatus, boolean)
	 *
	 * 2.1 存在保存点
	 * @see DefaultTransactionStatus#hasSavepoint()
	 * 直接回滚到保存点
	 *
	 * 2.2 新事务,说明异常时创建事务的那个方法抛出来的
	 * @see DefaultTransactionStatus#isNewTransaction()
	 * 直接进行回滚
	 *
	 * 2.3 不是创建事务抛出的并且存在事务
	 * @see DefaultTransactionStatus#hasTransaction()
	 * 设置回滚标识
	 * @see DataSourceTransactionManager#doSetRollbackOnly(DefaultTransactionStatus)
	 * 具体就是设置连接持有对象的标识位true
	 * @see org.springframework.jdbc.datasource.ConnectionHolder#rollbackOnly
	 *
	 * 四,提交
	 * @see TransactionAspectSupport#commitTransactionAfterReturning(TransactionAspectSupport.TransactionInfo)
	 * 具体
	 * @see AbstractPlatformTransactionManager#commit(TransactionStatus)
	 * 如果此时连接中的回滚标识位true
	 * 那么会进行强制回滚,并且抛出异常
	 * 这种情况只会在最初创建事务的方法中会抛出异常
	 * 因为多层嵌套情况下,每次都只会设置回滚标识
	 *
	 *
	 */
	@Test
	public void testTransactionCgLib() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoConfig.class);
		context.register(TransactionKanoA.class, TransactionKanoB.class);
		context.refresh();
		TransactionKanoA transactionKanoA = context.getBean(TransactionKanoA.class);
		transactionKanoA.testTransactionAA();

	}

	/**
	 * 首先判断线程上下文是否存在是否
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#isExistingTransaction(Object) 
	 * 存在
	 * 则创建一个新的事务
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#handleExistingTransaction(TransactionDefinition, Object, boolean) 
	 */
	@Test
	public void testTransactionNew() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoSingleConfig.class);
		context.register(TransactionKano.class);
		context.refresh();
		TransactionKano transactionKano = context.getBean(TransactionKano.class);
		transactionKano.testTransactionNew();
	}

	/**
	 * 如果当前线程存在事务则会挂起当前事务
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#handleExistingTransaction(TransactionDefinition, Object, boolean) 
	 * 
	 * 如果没有事务则走不启用事务逻辑
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#getTransaction(TransactionDefinition) 
	 */
	@Test
	public void testTransactionNotSupport() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoSingleConfig.class);
		context.register(TransactionKano.class);
		context.refresh();
		TransactionKano transactionKano = context.getBean(TransactionKano.class);
		transactionKano.testTransactionNotSupport();
	}


	/**
	 * 如果之前存在事务
	 * 1.如果支持保存点（默认）
	 * 那么会设置保存点来执行内部事务
	 * 2. 启用新事务 没有试过
	 * 创建一个新事务 只用于JTA
	 * 执行内部事务
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#handleExistingTransaction(TransactionDefinition, Object, boolean)
	 */
	@Test
	public void testTransactionNested() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoSingleConfig.class);
		context.register(TransactionKano.class);
		context.refresh();
		TransactionKano transactionKano = context.getBean(TransactionKano.class);
		transactionKano.testTransactionNested();
	}

	/**
	 * 开启事务的方法调用其他方法
	 * 其他方法执行异常，异常被调用者捕获
	 * 1. 嵌套事务
	 * 直接回滚到保存点
	 * 2. 开启事务方法抛出
	 * 直接回滚
	 * 3.其他方法抛出
	 * 设置异常回滚标识
	 * @see org.springframework.jdbc.datasource.ConnectionHolder#rollbackOnly
	 * @see org.springframework.transaction.interceptor.TransactionAspectSupport#completeTransactionAfterThrowing(TransactionAspectSupport.TransactionInfo, Throwable)
	 *
	 * 提交判断这个标识
	 * @see TransactionAspectSupport#commitTransactionAfterReturning(TransactionAspectSupport.TransactionInfo)
	 * 如果标识被设置那么强制回滚
	 */
	@Test
	public void testTransactionRollbackOnly() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoSingleConfig.class);
		context.register(TransactionKano.class);
		context.refresh();
		TransactionKano transactionKano = context.getBean(TransactionKano.class);
		transactionKano.testTransactionRollbackOnly();
	}

	@Test
	public void testTransactionRollbackOnlySecond() {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.register(KanoSingleConfig.class);
		context.register(TransactionKano.class);
		context.refresh();
		TransactionKano transactionKano = context.getBean(TransactionKano.class);
		transactionKano.testTransactionRollbackOnlySecond();
	}



}
