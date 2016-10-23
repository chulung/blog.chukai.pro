package com.chulung.blog.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.CommentsMapper;

@Component
public class CommonCronJob extends AbstractCronJob {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private CommentsMapper commentsMapper;

	@Override
	public void execute() throws Exception {
		// 重新计算评论数
		articleMapper.selectAll().parallelStream().forEach(e -> {
			commentsMapper.recalcCommentsCountForArticle(e.getId());
		});
	}

}
