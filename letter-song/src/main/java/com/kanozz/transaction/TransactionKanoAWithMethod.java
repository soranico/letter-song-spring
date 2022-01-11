package com.kanozz.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


public class TransactionKanoAWithMethod {
	private static final Logger log = LoggerFactory.getLogger(TransactionKanoAWithMethod.class);



	@Transactional
	public void  testTransactionAA(){
		log.info("i'm testTransactionAA");
		
	}




}
