package com.chulung.craft.service.impl;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.chulung.craft.model.User;
import com.chulung.craft.service.*;
import com.chulung.ccache.annotation.CCache;
import com.chulung.common.util.StringUtil;
import com.chulung.craft.dto.ArticleDto;
import com.chulung.craft.dto.CommonInfo;
import com.chulung.craft.enumerate.ConfigKeyEnum;
import com.chulung.craft.enumerate.IsDeleteEnum;
import com.chulung.craft.mapper.ArticleMapper;
import com.chulung.craft.model.Article;
import com.chulung.craft.model.ArticleDraft;
import com.chulung.search.ArticlesSearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chulung.craft.dto.ArticleFiling;
import com.chulung.craft.dto.PageIn;
import com.chulung.craft.enumerate.PublishStatusEnum;
import com.chulung.craft.exception.MethodRuntimeExcetion;
import com.chulung.craft.mapper.ArticleDraftHistoryMapper;
import com.chulung.craft.mapper.ArticleDraftMapper;
import com.chulung.craft.session.WebSessionSupport;
import com.chulung.common.util.NumberUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
	private static final int PAGE_SIZE = 4;

	@Resource
	private WebSessionSupport webSessionSupport;

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleDraftHistoryMapper articleDraftHistoryMapper;
	@Autowired
	private ArticleDraftMapper articleDraftMapper;

	@Autowired
	private ColumnTypeSevice columnTypeSevice;

	@Autowired
	private ConfigService configService;


	@Autowired
	private CommentsService commentsService;

    @Autowired
    private MetaClBlogLogService metaClBlogLogService;

	@Autowired
	private ArticlesSearchHandler articlesSearchHandler;


	public Article findArticleById(Integer id) {
		Article a = articleMapper.selectByPrimaryKey(id);
		if (a==null){
			throw new MethodRuntimeExcetion("拒绝访问");
		}
		if (a.getTypeId()==1) {
			a.setContext(a.getContext()+ (a.getTypeId()!=3 && StringUtil.isBlank(a.getLicense())?configService.getValueBykey(ConfigKeyEnum.RESET_SEARCH_INDEX, ConfigKeyEnum.ARTICLE_LICENSE.name()):a.getLicense()));
		}
		return a;
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
		// 转码html 防止其他
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
			pushBlog(articleDraft);
			articlesSearchHandler.index(article.getId());
		} else if (articleDraft.getArticleId() != null) {
			Article record = new Article();
			record.setId(articleDraft.getArticleId());
			record.setIsDelete(IsDeleteEnum.Y);
			this.articleMapper.updateByPrimaryKeySelective(record);
		}
		if (!(articleDraftMapper.updateByPrimaryKeySelective(articleDraft) == 1)) {
			throw new MethodRuntimeExcetion("修改草稿失败");
		}
		return true;
	}

	/**
	 * 推送博客
	 * @param articleDraft
	 */
	private void pushBlog(ArticleDraft articleDraft) {
		if (articleDraft.getArticleId()!=null && articleDraft.getPushBlog()==1){
            try {
                metaClBlogLogService.pushBlog();
            } catch (Exception e) {
                logger.error("同步文章失败 articleDraftId={},err={})",articleDraft.getId(),e);
            }
        }
	}

	@Override
	@Transactional
	public Integer insert(ArticleDraft articleDraft) {
		try {
			User user = this.webSessionSupport.getCurUser().get();
			articleDraft.setUpdateTime(LocalDateTime.now());
			articleDraft.setVersion(1);
			articleDraft.setAuthor(user.getNickName());
			articleDraft.setIsDelete(IsDeleteEnum.N);
			articleDraft.setCreateTime(LocalDateTime.now());
			if (PublishStatusEnum.Y == articleDraft.getIsPublish()) {
				Article article = Article.of(articleDraft);
				if (articleMapper.insertSelective(article)<= 0) {
					throw new MethodRuntimeExcetion("插入文章失败");
				}
				articleDraft.setArticleId(article.getId());
				pushBlog(articleDraft);
				articlesSearchHandler.index(article.getId());
			}
			if (this.articleDraftMapper.insertSelective(articleDraft) <= 0) {
				throw new MethodRuntimeExcetion("插入草稿失败");
			}
			return articleDraft.getId();
		} catch (DuplicateKeyException e) {
			throw new MethodRuntimeExcetion("文章已存在");
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
		throw new MethodRuntimeExcetion("草稿不存在,id=" + id);
	}

	private String generatingSummary(String context) {
		String replaceAll = context.replaceAll("</?.*?>", "");
		return replaceAll.length() > 120 ? replaceAll.substring(0, 120) + "..." : replaceAll;
	}

	public PageInfo<Article> selectBySelectiveForArticle(Optional<Integer> startPage, Integer typeId) {
		ArticleDto bean = new ArticleDto();
		bean.setTypeId(typeId);
		bean.setIsDelete(IsDeleteEnum.N);
		PageHelper.startPage(startPage.orElse(1), PAGE_SIZE);
		Page<Article> page = (Page<Article>) articleMapper.selectBySelectiveForBlog(bean);
		PageInfo<Article> info = new PageInfo<Article>();
		info.setList(convertToSummary(page));
		info.setTotal(page.getTotal());
		info.setPages(page.getPages());
		return info;
	}

	@Override
	public List<Article> getArticlesByYearMonth(Integer year, Integer month) {
		if (NumberUtil.isRangeNotIn(year, 2014, 2993) || NumberUtil.isRangeNotIn(month, 1, 12)) {
			return Collections.emptyList();
		}
		ArticleDto bean = new ArticleDto();
		bean.setCreateTimeStart(LocalDateTime.of(year, month, 1, 0, 0));
		bean.setCreateTimeEnd(LocalDateTime.of(year, month, 1, 0, 0).plus(1, ChronoUnit.MONTHS));
		return convertToSummary(articleMapper.selectBySelectiveForBlog(bean));
	}

	@Override
	@CCache(liveSeconds = 3600)
	public CommonInfo getCommonInfo() {
		// 归档信息
		List<ArticleFiling> list = new ArrayList<ArticleFiling>();
		ArticleDto bean = new ArticleDto();
		bean.setIsDelete(IsDeleteEnum.N);
		articleMapper.selectBySelectiveForBlog(bean).parallelStream().map(article -> article.getCreateTime())
				.map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonthValue()))
				.collect(Collectors.groupingBy(yearMonth -> yearMonth, Collectors.counting())).forEach((k, v) -> {
					list.add(new ArticleFiling(k, v.intValue()));
				});
		list.sort((o1, o2) -> {
			return o2.compareTo(o1);
		});
		CommonInfo commonInfo = new CommonInfo(list);
		commonInfo.setPopularArticles(this.listPopularArticles());
		commonInfo.setRecentlyComments(this.commentsService.listRecentlyComments());
		return commonInfo;
	}

	public List<Article> convertToSummary(List<Article> articles) {
		return articles.stream().map(a -> {
			a.setContext(generatingSummary(a.getContext()));
            a.setTypeName(this.columnTypeSevice.getIdColumnMap().get(a.getTypeId()).getCnName());
			return a;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn) {
		PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
		return this.articleDraftMapper.selectTileList(new ArticleDraft());
	}

	@Override
	public List<Article> listPopularArticles(){
		PageHelper.startPage(1,3,"id desc");
		Article record=new Article();
		record.setTypeId(1);
		return this.convertToSummary(this.articleMapper.select(record));

	}
}