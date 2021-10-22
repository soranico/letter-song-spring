package com.kanozz.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TransactionKanoB {
	private static final Logger log = LoggerFactory.getLogger(TransactionKanoB.class);

	private TransactionKanoB transactionKanoB;

	public void  testTransactionBA(){

		log.info("i'm testTransactionBA");

		log.info("call testTransactionBB start");
		transactionKanoB.testTransactionBB();
		log.info("call testTransactionBB end");
	}

	public void  testTransactionBB(){
		log.info("i'm testTransactionBB");
	}
}
