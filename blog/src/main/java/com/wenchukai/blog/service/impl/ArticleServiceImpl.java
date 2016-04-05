package com.wenchukai.blog.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wenchukai.blog.dto.PageIn;
import com.wenchukai.blog.enumerate.PublishStatusEnum;
import com.wenchukai.blog.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.blog.mapper.ArticleDraftHistoryMapper;
import com.wenchukai.blog.mapper.ArticleDraftMapper;
import com.wenchukai.blog.mapper.ArticleMapper;
import com.wenchukai.blog.mapper.ArticleTypeMapper;
import com.wenchukai.blog.model.Article;
import com.wenchukai.blog.model.ArticleDraft;
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
	@Autowired
	private ArticleTypeMapper articleTypeMapper;

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
		if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			if (article.getId() == null) {
				article.setUserId(user.getId());
				article.setAuthor(user.getNickName());
				article.setCreateTime(LocalDateTime.now());
				Integer aId = articleMapper.insertSelective(article);
				articleDraft.setArticleId(aId);
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
	public List<ArticleType> findAllArticleTypes() {
		return articleTypeMapper.selectAll();
	}

	@Override
	public List<Article> findArticleTitleList(PageIn pageIn) {
		PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
		return this.articleMapper.selectTileList();
	}

	@Override
	public Integer findArticleDraftIdByArticleId(ArticleDraft articleDraft) {
		ArticleDraft queryOne = articleDraftMapper.selectByPrimaryKey(articleDraft.getId());
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
		if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			int key = 0;
			if ((key = articleMapper.insertSelective(article)) <= 0) {
				throw new RuntimeException("插入文章失败");
			}
			articleDraft.setArticleId(key);
		}
		if (this.articleDraftMapper.insertSelective(articleDraft) > 0) {
			throw new RuntimeException("插入草稿失败");
		}
	}

	@Override
	public List<ArticleDraft> findArticleDraftsListByAjax(PageIn pageIn) {
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
			if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
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
}
