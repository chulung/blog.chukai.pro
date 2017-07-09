package com.chulung.website.quartz.job;

import com.chulung.website.service.MetaClBlogLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class MetaClBlogCronJob extends AbstractCronJob {

	@Autowired
	private MetaClBlogLogService metaClBlogLogService;

	@Scheduled(cron = "0 0 9 * * ?")
	public void execute() throws Exception {
		this.metaClBlogLogService.pushBlog();

	}

}
