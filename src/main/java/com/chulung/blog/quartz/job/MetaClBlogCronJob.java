package com.chulung.blog.quartz.job;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chulung.blog.enumerate.ConfigKeyEnum;
import com.chulung.blog.enumerate.IsDeleteEnum;
import com.chulung.blog.enumerate.LogLevel;
import com.chulung.blog.enumerate.LogType;
import com.chulung.blog.enumerate.SiteEnum;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.MetaClBlogLogMapper;
import com.chulung.blog.model.AppLog;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.MetaClBlogLog;
import com.chulung.blog.service.ConfigService;
import com.chulung.common.util.DateUtils;
import com.chulung.metaclblog.MetaWeblog;
import com.chulung.metaclblog.struct.Post;

@Component
public class MetaClBlogCronJob extends AbstractCronJob {
	public static String METACKBLOG_COMMENTS = "<p>作者：chulung</p><p>原文链接:<a href=\"https://chulung.com/article/%s\">https://chulung.com/article/%s</a></p><p>本文由<a href=\"https://github.com/chulung/MetaCLblog\">MetaCLBlog</a>于%s自动同步至%s</p>";

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private MetaClBlogLogMapper metaWeBlogLogMapper;

	@Resource(name = "metaCLBlogList")
	private List<MetaWeblog> metaCLBlogList;
	@Autowired
	private ConfigService configService;

	@Override
	public void execute() throws Exception {
		for (MetaWeblog metaWeblog : metaCLBlogList) {
			for (Article article : articleMapper.selectListForMetaClblog(metaWeblog.getConfigInfo().getSiteName())) {
				this.pushArticle(article, metaWeblog);
			}
		}
	}

	/**
	 * 推送博客文章至其他网站
	 * 
	 * @param article
	 * @param metaWeblog
	 * @return
	 * @throws XmlRpcException
	 */
	@Transactional
	public boolean pushArticle(Article article, MetaWeblog metaWeblog) throws XmlRpcException {
		SiteEnum site = SiteEnum.valueOf(metaWeblog.getConfigInfo().getSiteName());
		MetaClBlogLog metaCLBlogLog = this.metaWeBlogLogMapper.selectOne(new MetaClBlogLog(article.getId(), site));
		Post post = new Post();
		post.setTitle(article.getTitle());
		post.setDateCreated(DateUtils.toDate(article.getCreateTime()));
		post.setDescription(article.getContext()
				+String.format(METACKBLOG_COMMENTS, article.getId(), article.getId(),
				DateUtils.format(LocalDateTime.now()), site.getDedcription()) +  configService.getValueBykey(ConfigKeyEnum.ARTICLE_LICENSE.name()));
		if (metaCLBlogLog != null) {
			if (article.getIsDelete() == IsDeleteEnum.Y) {
				metaWeblog.deletePost(metaCLBlogLog.getPostId());
			} else {
				post.setPostid(metaCLBlogLog.getPostId());
				// 发送编辑请求
				metaWeblog.editPost(post, true);
			}
			MetaClBlogLog record = new MetaClBlogLog();
			record.setId(metaCLBlogLog.getId());
			record.setLastestPostTime(LocalDateTime.now());
			metaWeBlogLogMapper.updateByPrimaryKeySelective(record);
			cronJobLogMapper.insertSelective(new AppLog(LogType.META_CK_BLOG_LOG, LogLevel.INFO,
					String.format("博客《%s》更新推送成功", post.getTitle())));
		} else {
			// 发送新建博客请求
			String postId = metaWeblog.newPost(article.getId().toString(), post, true);
			MetaClBlogLog record = new MetaClBlogLog(postId, article.getId(), LocalDateTime.now(), site);
			metaWeBlogLogMapper.insertSelective(record);
			cronJobLogMapper.insertSelective(new AppLog(LogType.META_CK_BLOG_LOG, LogLevel.INFO,
					String.format("博客《%s》新建推送成功", post.getTitle())));
		}
		return true;

	}
}
