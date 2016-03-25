package com.wck.blog.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wck.blog.bean.Article;
import com.wck.blog.bean.ArticleDraft;
import com.wck.blog.bean.ArticleType;
import com.wck.blog.bean.User;
import com.wck.blog.enumerate.PublishStatusEnum;
import com.wck.blog.service.ArticleService;
import com.wck.blog.util.WebSessionSupport;
import com.wck.cache.annotation.Cache;
import com.wck.durable.bean.PageIn;
import com.wck.durable.transaction.annotation.Transaction;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
	@Resource
	private WebSessionSupport webSessionSupport;

	public Article findArticleById(Integer id) {
		return session.queryOne(new Article(id));
	}

	@Override
	@Transaction
	public boolean update(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		// 备份老版本
		session.execute("insert into ArticleDraftHistory select * from ArticleDraft where id=?", articleDraft.getId());
		articleDraft.setArticleId(session
				.queryOne("select articleId from ArticleDraft where id=?", ArticleDraft.class, articleDraft.getId())
				.getArticleId());
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(articleDraft.getVersion() + 1);
		articleDraft.setAuthor(user.getNickName());
		articleDraft.setIsDelete(0);
		articleDraft.setUserId(this.webSessionSupport.getCurUserId().get());
		articleDraft.setCreateTime(LocalDateTime.now());
		// 判断是否发布文章
		if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
			Article article = Article.of(articleDraft);
			if (!(article.getId() == null ? (session.insert(article) != null) : session.update(article))) {
				throw new RuntimeException("新建或修改文章失败");
			}
		}
		if (!session.update(articleDraft)) {
			throw new RuntimeException("修改草稿失败");
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
	public Integer findArticleDraftIdByArticleIdAndVersion(ArticleDraft articleDraft) {
		ArticleDraft queryOne = session.queryOne("select id from ArticleDraft where articleId=? and version =?",
				ArticleDraft.class, articleDraft.getArticleId(), articleDraft.getVersion());
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
	public void delete(Integer id) {
		ArticleDraft articleDraft = session
				.queryOne("select id,articleId,version,isPublish from ArticleDraft where id=?", ArticleDraft.class, id);
		if (articleDraft != null) {
			if (PublishStatusEnum.PUBLISH.getCode().equals(articleDraft.getIsPublish())) {
				session.execute("update Article set isdelete=1 where id=? and version=?", articleDraft.getArticleId(),
						articleDraft.getVersion());
			}
			session.execute("update ArticleDraft set isdelete=1 where id=? and version=?", articleDraft.getId(),
					articleDraft.getVersion());
		}
	}
}
