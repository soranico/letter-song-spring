package com.kanozz.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class QuartzKanoConfig {


	@Bean
	public JobDetail jobDetail (){
		return JobBuilder.newJob(QuartzKanoA.class).build();
	}

	@Bean
	public Trigger trigger(JobDetail jobDetail) throws Exception {
		ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		return TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(scheduleBuilder).startNow().build();
	}

	@Bean
	public Scheduler scheduler() throws  Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		return scheduler;
	}







}
