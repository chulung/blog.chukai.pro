package com.chulung.craft.quartz.job;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import com.chulung.craft.enumerate.ConfigKeyEnum;
import com.chulung.craft.service.ConfigService;
import com.chulung.craft.service.MetaClBlogLogService;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chulung.craft.enumerate.IsDeleteEnum;
import com.chulung.craft.enumerate.LogLevel;
import com.chulung.craft.enumerate.LogType;
import com.chulung.craft.enumerate.SiteEnum;
import com.chulung.craft.mapper.ArticleMapper;
import com.chulung.craft.mapper.MetaClBlogLogMapper;
import com.chulung.craft.model.AppLog;
import com.chulung.craft.model.Article;
import com.chulung.craft.model.MetaClBlogLog;
import com.chulung.common.util.DateUtils;
import com.chulung.metaclblog.MetaWeblog;
import com.chulung.metaclblog.struct.Post;

@Component
public class MetaClBlogCronJob extends AbstractCronJob {

	@Autowired
	private MetaClBlogLogService metaClBlogLogService;

	@Override
	public void execute() throws Exception {
		this.metaClBlogLogService.pushBlog();

	}


}
