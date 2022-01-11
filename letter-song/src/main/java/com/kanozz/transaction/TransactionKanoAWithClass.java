package com.kanozz.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


public class TransactionKanoAWithClass {

	private static final Logger log = LoggerFactory.getLogger(TransactionKanoAWithClass.class);



	@Transactional
	public void  testTransactionAA(){
		log.info("i'm testTransactionAA");

	}




}
