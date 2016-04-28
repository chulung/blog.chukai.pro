package com.wenchukai.quartz.job;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.wenchukai.common.util.DateUtils;

@Component(value="testJob")
public class TestJob extends CronJob{

	@Override
	public void execute() {
		System.out.println(DateUtils.format(new Date()));
	}

}
