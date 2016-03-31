package com.wenchukai.blog.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenchukai.blog.dto.PageIn;
import com.wenchukai.blog.enumerate.PublishStatusEnum;
import com.wenchukai.blog.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.blog.mapper.ArticleDraftHistoryMapper;
import com.wenchukai.blog.mapper.ArticleDraftMapper;
import com.wenchukai.blog.mapper.ArticleMapper;
import com.wenchukai.blog.model.Article;
import com.wenchukai.blog.model.ArticleDraft;
import com.wenchukai.blog.model.ArticleDraftHistory;
import com.wenchukai.blog.model.ArticleDraftWithBLOBs;
import com.wenchukai.blog.model.ArticleType;
import com.wenchukai.blog.model.User;
import com.wenchukai.blog.service.ArticleService;
import com.wenchukai.blog.util.WebSessionSupport;
import com.wenchukai.cache.annotation.Cache;

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

	public Article findArticleById(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	// @Transaction
	public boolean update(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		// 备份老版本
		ArticleDraft oldDraft = articleDraftMapper.selectByPrimaryKey(articleDraft.getId());
		articleDraftHistoryMapper.insert(oldDraft);
		articleDraft.setArticleId(oldDraft.getArticleId());
		articleDraft.setUpdateTime(new Date());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(oldDraft.getVersion() + 1);
		// 判断是否发布文章
		if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			if (article.getId() == null) {
				article.setUserId(user.getId());
				article.setAuthor(user.getNickName());
				article.setCreateTime(LocalDateTime.now());
				Integer aId = session.insert(article);
				articleDraft.setArticleId(aId);
			} else {
				session.update(article);
			}
		}
		if (!session.update(articleDraft)) {
			throw new GlobalMethodRuntimeExcetion("修改草稿失败");
		}
		return true;
	}

	@Cache(key = "findAllArticleTypes", timeToLive = 30)
	@Override
	public List<ArticleType> findAllArticleTypes() {
		return session.queryList(new ArticleType());
	}

	@Override
	public List<Article> findArticlesListByAjax(PageIn pageIn) {
		return session.queryList("select id,title,createTime,updateTime,author,version from Article " + pageIn,
				Article.class);
	}

	@Override
	public Integer findArticleDraftIdByArticleId(ArticleDraft articleDraft) {
		ArticleDraft queryOne = session.queryOne("select id from ArticleDraft where articleId=? ", ArticleDraft.class,
				articleDraft.getArticleId());
		return queryOne != null ? queryOne.getId() : null;
	}

	@Override
	@Transaction
	public void insert(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(1);
		articleDraft.setAuthor(user.getNickName());
		articleDraft.setIsDelete(0);
		articleDraft.setUserId(this.webSessionSupport.getCurUserId().get());
		articleDraft.setCreateTime(LocalDateTime.now());
		if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			Integer key = null;
			if ((key = session.insert(article)) == null) {
				throw new RuntimeException("插入文章失败");
			}
			articleDraft.setArticleId(key.intValue());
		}
		if (session.insert(articleDraft) == null) {
			throw new RuntimeException("插入草稿失败");
		}
	}

	@Override
	public List<ArticleDraft> findArticleDraftsListByAjax(PageIn pageIn) {
		return session.queryList(
				"select id,articleId,title,createTime,updateTime,author,mender,isPublish,version from ArticleDraft where isdelete=0 "
						+ pageIn,
				ArticleDraft.class);
	}

	@Override
	public ArticleDraft findArticleDraft(ArticleDraft articleDraft) {
		return this.session.queryOne(articleDraft);
	}

	@Override
	@Transaction
	public void deleteArticleDraft(Integer id) {
		ArticleDraft articleDraft = session.queryOne("select id,articleId,isPublish from ArticleDraft where id=?",
				ArticleDraft.class, id);
		if (articleDraft != null) {
			if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
				session.execute("update Article set isdelete=1 where id=? ", articleDraft.getArticleId());
			}
			session.execute("update ArticleDraft set isdelete=1 where id=? ", articleDraft.getId());
		}
		throw new GlobalMethodRuntimeExcetion("草稿不存在,id=" + id);
	}
}
