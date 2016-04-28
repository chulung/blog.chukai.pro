package com.wenchukai.blog.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wenchukai.blog.constant.Constants;
import com.wenchukai.blog.dto.PageIn;
import com.wenchukai.blog.enumerate.DictionaryTypeEnum;
import com.wenchukai.blog.enumerate.PublishStatusEnum;
import com.wenchukai.blog.enumerate.SiteEnum;
import com.wenchukai.blog.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.blog.mapper.ArticleDraftHistoryMapper;
import com.wenchukai.blog.mapper.ArticleDraftMapper;
import com.wenchukai.blog.mapper.ArticleMapper;
import com.wenchukai.blog.mapper.DictionaryMapper;
import com.wenchukai.blog.mapper.MetaCKBlogLogMapper;
import com.wenchukai.blog.model.Article;
import com.wenchukai.blog.model.ArticleDraft;
import com.wenchukai.blog.model.Dictionary;
import com.wenchukai.blog.model.MetaCKBlogLog;
import com.wenchukai.blog.model.User;
import com.wenchukai.blog.service.ArticleService;
import com.wenchukai.blog.session.WebSessionSupport;
import com.wenchukai.cache.annotation.Cache;
import com.wenchukai.common.util.DateUtils;
import com.wenchukai.metackblog.MetaWeblog;
import com.wenchukai.metackblog.struct.Post;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
	@Resource
	private WebSessionSupport webSessionSupport;

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleDraftHistoryMapper articleDraftHistoryMapper;
	@Autowired
	private ArticleDraftMapper articleDraftMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private MetaCKBlogLogMapper metaWeBlogLogMapper;
	/**
	 * 博客园的metaweblog接口
	 */
	@Resource(name = "cnblogMetaWeblog")
	private MetaWeblog cnblogMetaWeblog;

	public Article findArticleById(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public boolean update(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		// 备份老版本
		articleDraftHistoryMapper.insertToArticleDraftHistory(articleDraft.getId());
		ArticleDraft oldDraft = this.articleDraftMapper.selectByPrimaryKey(articleDraft.getId());
		articleDraft.setArticleId(oldDraft.getArticleId());
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(oldDraft.getVersion() + 1);
		// 判断是否发布文章
		if (PublishStatusEnum.PUBLISHED.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			if (article.getId() == null) {
				article.setUserId(user.getId());
				article.setAuthor(user.getNickName());
				article.setCreateTime(LocalDateTime.now());
				articleMapper.insertSelective(article);
				articleDraft.setArticleId(article.getId());
			} else {
				articleMapper.updateByPrimaryKeySelective(article);
			}
		}
		if (!(articleDraftMapper.updateByPrimaryKeySelective(articleDraft) == 1)) {
			throw new GlobalMethodRuntimeExcetion("修改草稿失败");
		}
		return true;
	}

	@Cache(key = "findAllArticleTypes", timeToLive = 30)
	@Override
	public List<Dictionary> findAllArticleTypes() {
		Dictionary record = new Dictionary();
		record.setDictType(DictionaryTypeEnum.ARTICLE_TYPE);
		return dictionaryMapper.select(record);
	}

	@Override
	public List<Article> findArticleTitleList(PageIn<Article> pageIn) {
		PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
		return this.articleMapper.selectTileList();
	}

	@Override
	public Integer findArticleDraftIdByArticleId(ArticleDraft articleDraft) {
		if (articleDraft.getArticleId() == null) {
			return null;
		}
		ArticleDraft queryOne = articleDraftMapper.selectOne(articleDraft);
		return queryOne != null ? queryOne.getId() : null;
	}

	@Override
	@Transactional
	public void insert(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(1);
		articleDraft.setAuthor(user.getNickName());
		articleDraft.setIsDelete(0);
		articleDraft.setUserId(this.webSessionSupport.getCurUserId().get());
		articleDraft.setCreateTime(LocalDateTime.now());
		if (PublishStatusEnum.PUBLISHED.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			int key = 0;
			if ((key = articleMapper.insertSelective(article)) <= 0) {
				throw new RuntimeException("插入文章失败");
			}
			articleDraft.setArticleId(key);
		}
		if (this.articleDraftMapper.insertSelective(articleDraft) <= 0) {
			throw new RuntimeException("插入草稿失败");
		}
	}

	@Override
	public List<ArticleDraft> findArticleDraftsListByAjax(PageIn<ArticleDraft> pageIn) {
		PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
		return this.articleDraftMapper.selectTileList();
	}

	@Override
	public ArticleDraft findArticleDraft(Integer id) {
		return this.articleDraftMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void deleteArticleDraft(Integer id) {
		ArticleDraft articleDraft = this.articleDraftMapper.selectByPrimaryKey(id);
		if (articleDraft != null) {
			if (PublishStatusEnum.PUBLISHED.getCode().equals(articleDraft.getIsPublish())) {
				Article record = new Article();
				record.setId(articleDraft.getArticleId());
				record.setIsDelete(1);
			}
			ArticleDraft record = new ArticleDraft();
			record.setId(id);
			record.setIsDelete(1);
		}
		throw new GlobalMethodRuntimeExcetion("草稿不存在,id=" + id);
	}

	/**
	 * 推送博客文章至其他网站
	 * 
	 * @param article
	 * @return
	 */
	public boolean pushArticle(Article article) {
		MetaCKBlogLog metaCKBlogLog = this.metaWeBlogLogMapper.selectOne(new MetaCKBlogLog(article.getId()));
		Post post = new Post();
		post.setTitle(article.getTitle());
		post.setDateCreated(new Date());
		post.setDescription(article.getContext()
				+ String.format(Constants.METACKBLOG_COMMENTS, DateUtils.format(LocalDateTime.now()), article.getId()));
		if (metaCKBlogLog != null) {
			post.setPostid(metaCKBlogLog.getPostId());
			try {
				// 发送编辑请求
				cnblogMetaWeblog.editPost(post, true);
				MetaCKBlogLog record = new MetaCKBlogLog();
				record.setId(metaCKBlogLog.getId());
				record.setLastestPostTime(LocalDateTime.now());
				metaWeBlogLogMapper.updateByPrimaryKeySelective(record);
			} catch (XmlRpcException e) {
				logger.error("", e);
			}

		} else {
			try {
				// 发送新建博客请求
				String postId = cnblogMetaWeblog.newPost(article.getId().toString(), post, true);
				MetaCKBlogLog record = new MetaCKBlogLog(postId, article.getId(), LocalDateTime.now(),
						SiteEnum.CNBLOGS);
				metaWeBlogLogMapper.insertSelective(record);
			} catch (XmlRpcException e) {
				logger.error("", e);
			}
		}
		return true;

	}
}
