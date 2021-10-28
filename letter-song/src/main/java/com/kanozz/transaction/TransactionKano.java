package com.kanozz.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionKano {
	private static final Logger log = LoggerFactory.getLogger(TransactionKano.class);


	@Autowired
	private TransactionKano transactionKano;

	@Transactional
	public void testTransactionNotSupport() {
		transactionKano.testTransactionNotSupport("kano");
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void testTransactionNotSupport(String name) {

	}


	@Transactional
	public void testTransactionNew() {
		transactionKano.testTransactionNew("kano");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void testTransactionNew(String name) {

	}


	@Transactional
	public void testTransactionNested() {
		transactionKano.testTransactionNested("kano");
	}

	@Transactional(propagation = Propagation.NESTED)
	public void testTransactionNested(String name) {

	}


	@Transactional
	public void testTransactionRollbackOnly() {
		try {
			transactionKano.testTransactionRollbackOnly("kano");
		} catch (Exception e) {
			log.error("", e);
		}
		log.info("run end");
	}

	@Transactional
	public void testTransactionRollbackOnly(String kano) {
		Integer.parseInt(kano);
	}


	@Transactional
	public String testTransactionRollbackOnlySecond() {
		try {
			transactionKano.testTransactionRollbackOnlySecond("kano");

		} catch (Exception e) {
			log.error("", e);
		}
		return "kano";
	}

	@Transactional
	public String testTransactionRollbackOnlySecond(String kano) {
		try {
			transactionKano.testTransactionRollbackOnlySecond("kano", 18);
		} catch (Exception e) {
			log.error("", e);
		}
		return "kano";
	}

	@Transactional
	public String testTransactionRollbackOnlySecond(String kano, Integer hanser) {
		Integer.parseInt(kano);
		return "kano";
	}


}
