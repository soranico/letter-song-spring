package com.kanozz.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
class TransactionKanoA {

	private final TransactionKanoB transactionKanoB;
	TransactionKanoA(TransactionKanoB transactionKanoB){
		this.transactionKanoB = transactionKanoB;
	}

	@Transactional
	public void  testTransactionAA(){
		log.info("i'm testTransactionAA");

		log.info("call testTransactionAB start");
		testTransactionAB();
		log.info("call testTransactionAB end");

	}

	public void  testTransactionAB(){
		log.info("i'm testTransactionAB");

		log.info("call testTransactionBA start");
		transactionKanoB.testTransactionBA();
		log.info("call testTransactionBA end");

	}


}
