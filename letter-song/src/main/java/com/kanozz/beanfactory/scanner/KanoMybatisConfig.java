package com.kanozz.beanfactory.scanner;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@MapperScan(basePackages = "com.kanozz.beanfactory.scanner.mybatis")
//@MapperScan(basePackages = "com.kanozz.beanfactory.scanner.mybatis",sqlSessionFactoryRef = "kanoSqlSessionFactory")
//@MapperScan(basePackages = "com.kanozz.beanfactory.scanner.mybatis",sqlSessionTemplateRef = "kanoSessionTemplate")
class KanoMybatisConfig {

	@Bean
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("");
		return dataSource;
	}

	@Bean
	public SqlSessionFactory kanoSqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public TransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	/**
	 * 最终是由SQLSessionTemplate
	 * 默认会通过SqlSessFactory去创建
 	 */
//	@Bean
//	public SqlSessionTemplate kanoSessionTemplate(DataSource dataSource) throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//		SqlSessionFactory factory = sqlSessionFactoryBean.getObject();
//		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(factory);
//		return sqlSessionTemplate;
//	}
}
