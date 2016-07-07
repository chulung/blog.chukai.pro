package com.chulung.blog.service.impl;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chulung.blog.dto.ArticleDto;
import com.chulung.blog.dto.ArticleFiling;
import com.chulung.blog.dto.CommonInfo;
import com.chulung.blog.dto.PageIn;
import com.chulung.blog.enumerate.DictionaryTypeEnum;
import com.chulung.blog.enumerate.IsDeleteEnum;
import com.chulung.blog.enumerate.PublishStatusEnum;
import com.chulung.blog.exception.MethodValidateExcetion;
import com.chulung.blog.mapper.ArticleDraftHistoryMapper;
import com.chulung.blog.mapper.ArticleDraftMapper;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.DictionaryMapper;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.ArticleDraft;
import com.chulung.blog.model.Dictionary;
import com.chulung.blog.model.User;
import com.chulung.blog.service.ArticleService;
import com.chulung.blog.session.WebSessionSupport;
import com.chulung.common.util.NumberUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
	private static final int PAGE_SIZE = 10;

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
	private PegDownProcessor downProcessor = new PegDownProcessor();

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
		articleDraft.setVersion(oldDraft.getVersion() + 1);
		articleDraft.setHtmlContext(downProcessor.markdownToHtml(articleDraft.getContext()));
		// 判断是否发布文章
		if (PublishStatusEnum.Y == articleDraft.getIsPublish()) {
			Article article = Article.of(articleDraft);
			if (article.getId() == null) {
				article.setAuthor(user.getNickName());
				article.setCreateTime(LocalDateTime.now());
				articleMapper.insertSelective(article);
				articleDraft.setArticleId(article.getId());
			} else {
				articleMapper.updateByPrimaryKeySelective(article);
			}
		}else if (articleDraft.getArticleId()!=null) {
			Article record=new Article();
			record.setId(articleDraft.getArticleId());
			record.setIsDelete(IsDeleteEnum.Y);
			this.articleMapper.updateByPrimaryKeySelective(record);
		}
		if (!(articleDraftMapper.updateByPrimaryKeySelective(articleDraft) == 1)) {
			throw new MethodValidateExcetion("修改草稿失败");
		}
		return true;
	}

	@Override
	public List<Dictionary> findAllArticleTypes() {
		Dictionary record = new Dictionary();
		record.setDictType(DictionaryTypeEnum.ARTICLE_TYPE);
		return dictionaryMapper.select(record);
	}

	@Override
	@Transactional
	public void insert(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setVersion(1);
		articleDraft.setAuthor(user.getNickName());
		articleDraft.setIsDelete(IsDeleteEnum.N);
		articleDraft.setCreateTime(LocalDateTime.now());
		articleDraft.setHtmlContext(downProcessor.markdownToHtml(articleDraft.getContext()));
		if (PublishStatusEnum.Y == articleDraft.getIsPublish()) {
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
	public ArticleDraft findArticleDraft(Integer id) {
		return id == null ? null : this.articleDraftMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void deleteArticleDraft(Integer id) {
		ArticleDraft articleDraft = this.articleDraftMapper.selectByPrimaryKey(id);
		if (articleDraft != null) {
			if (PublishStatusEnum.Y == articleDraft.getIsPublish()) {
				Article record = new Article();
				record.setId(articleDraft.getArticleId());
				record.setIsDelete(IsDeleteEnum.Y);
				this.articleMapper.updateByPrimaryKeySelective(record);
			}
			ArticleDraft record = new ArticleDraft();
			record.setId(id);
			record.setIsDelete(IsDeleteEnum.Y);
			this.articleDraftMapper.updateByPrimaryKeySelective(record);
		}
		throw new MethodValidateExcetion("草稿不存在,id=" + id);
	}

	

	private String generatingSummary(String context) {
		String replaceAll = context.replaceAll("</?.*?>", "");
		return replaceAll.length() > 120 ? replaceAll.substring(0, 120) + "..." : replaceAll;
	}

	public PageInfo<Article> selectBySelectiveForBlog(Optional<Integer> startPage, Integer typeId) {
		ArticleDto bean = new ArticleDto();
		bean.setTypeId(typeId);
		bean.setIsDelete(IsDeleteEnum.N);
		PageHelper.startPage(startPage.get(), PAGE_SIZE);
		Page<Article> page = (Page<Article>) articleMapper.selectBySelectiveForBlog(bean);
		PageInfo<Article> info = new PageInfo<Article>();
		info.setList(convertToSummary(page));
		info.setTotal(page.getTotal());
		return info;
	}

	@Override
	public List<Article> getBlogsByYearMonth(Integer year, Integer month) {
		if (NumberUtil.isRangeNotIn(year, 2014, 2993) && NumberUtil.isRangeNotIn(month, 1, 12)) {
			return Collections.emptyList();
		}
		ArticleDto bean = new ArticleDto();
		bean.setCreateTimeStart(LocalDateTime.of(year, month, 1, 0, 0));
		bean.setCreateTimeEnd(LocalDateTime.of(year, month, 1, 0, 0).plus(1, ChronoUnit.MONTHS));
		return convertToSummary(articleMapper.selectBySelectiveForBlog(bean));
	}

	@Override
	public CommonInfo getCommonInfo() {
		// 归档信息
		List<ArticleFiling> list = new ArrayList<ArticleFiling>();
		ArticleDto bean = new ArticleDto();
		bean.setIsDelete(IsDeleteEnum.N);
		articleMapper.selectBySelectiveForBlog(bean).stream().parallel().map(article -> article.getCreateTime())
				.map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonthValue()))
				.collect(Collectors.groupingByConcurrent(yearMonth -> yearMonth, Collectors.counting()))
				.forEach((k, v) -> {
					list.add(new ArticleFiling(k, v.intValue()));
				});
		list.sort((o1, o2) -> {
			return o2.compareTo(o1);
		});
		return new CommonInfo(list);
	}

	public List<Article> convertToSummary(List<Article> articles) {
		return articles.stream().map(a -> {
			a.setContext(generatingSummary(a.getContext()));
			return a;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn) {
		PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
		return this.articleDraftMapper.selectTileList(new ArticleDraft());
	}
}
