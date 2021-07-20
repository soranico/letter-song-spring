package com.kanozz.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
class TransactionKanoC {
	@Autowired
	private TransactionKanoB transactionKanoB;

	public void testTransactionCA(){
		log.info("i'm testTransactionCA");

		log.info("call testTransactionBB start");

		transactionKanoB.testTransactionBB();
		log.info("call testTransactionCA end");
	}
}
