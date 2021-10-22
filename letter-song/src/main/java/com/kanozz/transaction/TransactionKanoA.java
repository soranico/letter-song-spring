package com.kanozz.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionKanoA {
	private static final Logger log = LoggerFactory.getLogger(TransactionKanoA.class);

	private final TransactionKanoB transactionKanoB;

	TransactionKanoA(TransactionKanoB transactionKanoB){
		this.transactionKanoB = transactionKanoB;
	}

	@Transactional
	public void  testTransactionAA(){
		log.info("i'm testTransactionAA");

		log.info("call testTransactionAB start");
//		testTransactionAB();
		log.info("call testTransactionAB end");

	}

	public void  testTransactionAB(){
		log.info("i'm testTransactionAB");

		testTransactionAA();
		// 等价 当前this 非代理对象
//		this.testTransactionAA();

		log.info("transaction = {}",TransactionSynchronizationManager.getCurrentTransactionName());
		log.info("call testTransactionBA start");

//		transactionKanoB.testTransactionBA();
		log.info("call testTransactionBA end");

	}


}
