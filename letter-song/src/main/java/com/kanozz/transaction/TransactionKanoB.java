package com.kanozz.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
class TransactionKanoB {

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
