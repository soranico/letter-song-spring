package com.kanozz.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


class QuartzKanoA extends QuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(QuartzKanoA.class);
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("schedule");
	}

	@PostConstruct
	public void init(){
		log.info("waiting 10s");
		try {
			TimeUnit.SECONDS.sleep(60);
		}catch (Exception e){

		}
		log.info("10s end");
	}



}
