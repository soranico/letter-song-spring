package com.kanozz.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Slf4j
class QuartzKanoA extends QuartzJobBean {

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
