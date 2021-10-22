package com.kanozz.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class TransactionKanoC {
	private static final Logger log = LoggerFactory.getLogger(TransactionKanoC.class);
	@Autowired
	private TransactionKanoB transactionKanoB;

	public void testTransactionCA(){
		log.info("i'm testTransactionCA");

		log.info("call testTransactionBB start");

		transactionKanoB.testTransactionBB();
		log.info("call testTransactionCA end");
	}
}
