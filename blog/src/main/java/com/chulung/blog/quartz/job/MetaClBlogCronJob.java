package com.chulung.blog.quartz.job;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chulung.blog.enumerate.IsDeleteEnum;
import com.chulung.blog.enumerate.SiteEnum;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.MetaClBlogLogMapper;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.MetaClBlogLog;
import com.chulung.common.util.DateUtils;
import com.chulung.metaclblog.MetaWeblog;
import com.chulung.metaclblog.struct.Post;

@Component
public class MetaClBlogCronJob extends AbstractCronJob {
	public static String METACKBLOG_COMMENTS = "<p>本文于%s从<a href=\"https://blog.chulung.com\">Chu Lung's blog</a>自动同步同步,<a href=\"http://blog.chulung.com/article/%s\">访问原文</a><p>";

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private MetaClBlogLogMapper metaWeBlogLogMapper;
	/**
	 * 博客园的metaweblog接口
	 */
	@Resource(name = "cnblogMetaWeblog")
	private MetaWeblog cnblogMetaWeblog;

	@Override
	public void execute() throws Exception {
		for (Article article : articleMapper.selectListForMetaClblog().subList(0, 1)) {
			this.pushArticle(article);
		}
	}

	/**
	 * 推送博客文章至其他网站
	 * 
	 * @param article
	 * @return
	 * @throws XmlRpcException
	 */
	@Transactional
	public boolean pushArticle(Article article) throws XmlRpcException {
		MetaClBlogLog metaCLBlogLog = this.metaWeBlogLogMapper.selectOne(new MetaClBlogLog(article.getId()));
		Post post = new Post();
		post.setTitle(article.getTitle());
		post.setDateCreated(DateUtils.toDate(article.getCreateTime()));
		post.setDescription(article.getContext()
				+ String.format(METACKBLOG_COMMENTS, DateUtils.format(LocalDateTime.now()), article.getId()));
		if (metaCLBlogLog != null) {
			if (article.getIsDelete() == IsDeleteEnum.Y) {
				cnblogMetaWeblog.deletePost(metaCLBlogLog.getPostId());
			} else {
				post.setPostid(metaCLBlogLog.getPostId());
				// 发送编辑请求
				cnblogMetaWeblog.editPost(post, true);
			}
			MetaClBlogLog record = new MetaClBlogLog();
			record.setId(metaCLBlogLog.getId());
			record.setLastestPostTime(LocalDateTime.now());
			metaWeBlogLogMapper.updateByPrimaryKeySelective(record);
		} else {
			// 发送新建博客请求
			String postId = cnblogMetaWeblog.newPost(article.getId().toString(), post, true);
			MetaClBlogLog record = new MetaClBlogLog(postId, article.getId(), LocalDateTime.now(), SiteEnum.CNBLOGS);
			metaWeBlogLogMapper.insertSelective(record);
		}
		return true;

	}
}
