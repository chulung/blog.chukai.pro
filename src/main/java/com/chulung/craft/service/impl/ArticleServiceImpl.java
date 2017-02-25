package com.chulung.craft.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.chulung.common.util.ArticleBuilder;
import com.chulung.craft.mapper.ArticleTagMapper;
import com.chulung.craft.model.ArticleTag;
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
import com.hankcs.hanlp.dictionary.py.SYTDictionary;
import org.apache.commons.collections.CollectionUtils;
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
    private static final int PAGE_SIZE = 5;

    @Resource
    private WebSessionSupport webSessionSupport;

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleDraftHistoryMapper articleDraftHistoryMapper;
    @Autowired
    private ArticleDraftMapper articleDraftMapper;

    @Autowired
    private ConfigService configService;


    @Autowired
    private CommentsService commentsService;

    @Autowired
    private MetaClBlogLogService metaClBlogLogService;

    @Autowired
    private ArticlesSearchHandler articlesSearchHandler;

    @Autowired
    private ArticleBuilder articleBuilder;
    @Autowired
    private ColumnTypeSevice columnTypeSevice;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    public Article findArticleById(Integer id) {
        Article a = articleMapper.selectByPrimaryKey(id);
        if (a == null) {
            throw new MethodRuntimeExcetion("拒绝访问");
        }
        if (id == 20) {
            double wook = (Instant.now().getEpochSecond() - Instant.parse("2015-03-01T09:00:00.00Z").getEpochSecond())
                    / 31536000.0;
            wook = new BigDecimal(wook).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            int pinyin = (int) ((Instant.now().getEpochSecond() - Instant.parse("1867-01-01T00:00:00.00Z").getEpochSecond())
                    / 31536000);
            a.setContent(String.format(a.getContent(), pinyin, wook));
        }
        if (a.getTypeId() == 1) {
            a.setContent(a.getContent() + (a.getTypeId() != 3 && StringUtil.isBlank(a.getLicense()) ? configService.getValueBykey(ConfigKeyEnum.ARTICLE_LICENSE, ConfigKeyEnum.ARTICLE_LICENSE.name()) : a.getLicense()));
        }
        return a;
    }

    @Override
    @Transactional
    public boolean update(ArticleDraft articleDraft) {

        // 备份老版本
        articleDraftHistoryMapper.insertToArticleDraftHistory(articleDraft.getId());
        ArticleDraft oldDraft = this.articleDraftMapper.selectByPrimaryKey(articleDraft.getId());
        articleDraft.setArticleId(oldDraft.getArticleId());
        articleDraft.setUpdateTime(LocalDateTime.now());
        articleDraft.setVersion(oldDraft.getVersion() + 1);
        this.updateArticle(articleDraft);
        if (!(articleDraftMapper.updateByPrimaryKeySelective(articleDraft) == 1)) {
            throw new MethodRuntimeExcetion("修改草稿失败");
        }
        return true;
    }

    @Override
    /**
     * 根据草稿更新文章表
     */
    public Article updateArticle(ArticleDraft articleDraft) {
        if (articleDraft.getArticleId() != null) {
            //清除tag
            ArticleTag aTag = new ArticleTag();
            aTag.setArticleId(articleDraft.getArticleId());
            articleTagMapper.delete(aTag);
        }
        // 判断是否发布文章
        if (PublishStatusEnum.Y == articleDraft.getIsPublish()) {
            User user = this.webSessionSupport.getCurUser().get();
            Article article = articleBuilder.buildeFromDraft(articleDraft);
            article.setIsDelete(IsDeleteEnum.N);
            if (article.getId() == null) {
                article.setAuthor(user.getNickName());
                articleMapper.insertSelective(article);
                articleDraft.setArticleId(article.getId());
            } else {
                //不修改创建日期
                article.setCreateTime(null);
                articleMapper.updateByPrimaryKeySelective(article);
            }
            if (StringUtil.isNotBlank(article.getTags())) {
                Arrays.asList(article.getTags().split(",")).stream().forEach(t -> {
                    ArticleTag tag = new ArticleTag();
                    tag.setArticleId(article.getId());
                    tag.setTagName(t);
                    articleTagMapper.insertSelective(tag);
                });
            }
            pushBlog(articleDraft);
            articlesSearchHandler.index(article.getId());
            return article;
        } else if (articleDraft.getArticleId() != null) {
            Article record = new Article();
            record.setId(articleDraft.getArticleId());
            record.setIsDelete(IsDeleteEnum.Y);
            this.articleMapper.updateByPrimaryKeySelective(record);
        }
        return null;
    }

    /**
     * 推送博客
     *
     * @param articleDraft
     */
    private void pushBlog(ArticleDraft articleDraft) {
        if (articleDraft.getArticleId() != null && articleDraft.getPushBlog() == 1) {
            try {
                metaClBlogLogService.pushBlog();
            } catch (Exception e) {
                logger.error("同步文章失败 articleDraftId={},err={})", articleDraft.getId(), e);
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
            this.updateArticle(articleDraft);
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
        } else {
            throw new MethodRuntimeExcetion("草稿不存在,id=" + id);
        }
    }


    public PageInfo<Article> selectBySelectiveForArticle(Optional<Integer> startPage, Integer typeId) {
        ArticleDto bean = new ArticleDto();
        bean.setTypeId(typeId);
        bean.setIsDelete(IsDeleteEnum.N);
        PageHelper.startPage(startPage.orElse(1), PAGE_SIZE);
        Page<Article> page = (Page<Article>) articleMapper.selectSummarys(bean);
        PageInfo<Article> info = new PageInfo<>();
        info.setList(page);
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
        return articleMapper.selectSummarys(bean);
    }

    @Override
    @CCache(liveSeconds = 3600)
    public CommonInfo getCommonInfo() {
        // 归档信息
        List<ArticleFiling> list = new ArrayList<>();
        ArticleDto bean = new ArticleDto();
        bean.setIsDelete(IsDeleteEnum.N);
        //按月统计文章数量
        articleMapper.selectSummarys(bean).parallelStream().map(Article::getCreateTime)
                .map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonthValue()))
                .collect(Collectors.groupingBy(yearMonth -> yearMonth, Collectors.counting())).forEach((k, v) -> list.add(new ArticleFiling(k, v.intValue())));
        list.sort((o1, o2) -> o2.compareTo(o1));
        CommonInfo commonInfo = new CommonInfo(list);
        commonInfo.setPopularArticles(this.listPopularArticles());
        commonInfo.setRecentlyComments(this.commentsService.listRecentlyComments());
        commonInfo.setTags(this.articleTagMapper.selectAllTags());
        String recommendedArticleIds = this.configService.getValueBykey(ConfigKeyEnum.RECOMMENDED_ARTICLE_IDS);
        if (StringUtil.isNoneBlank(recommendedArticleIds)) {
            ArticleDto dto = new ArticleDto();
            dto.setIds(Arrays.asList(recommendedArticleIds.split(",")).stream().map(s -> {
                return Integer.valueOf(s);
            }).collect(Collectors.toList()));
            commonInfo.setRecommendedArticles(this.articleMapper.selectSummarys(dto));
        }
        return commonInfo;
    }

    @Override
    public List<ArticleDraft> findArticleDraftsList(PageIn<ArticleDraft> pageIn) {
        PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
        return this.articleDraftMapper.selectTileList(new ArticleDraft());
    }

    @Override
    public List<Article> listPopularArticles() {
        PageHelper.startPage(1, 4);
        return this.articleMapper.listPopularArticles();

    }

    @Override
    public List<Article> getArticlesByTagName(String tagName) {
        ArticleTag record = new ArticleTag();
        record.setTagName(tagName);
        List<Integer> articleIds = this.articleTagMapper.select(record).stream().map(t -> {
            return t.getArticleId();
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(articleIds)) return Collections.emptyList();
        ArticleDto art = new ArticleDto();
        art.setIds(articleIds);
        return this.articleMapper.selectSummarys(art);
    }

    @Override
    public List<Article> listRelevancy(Integer id) {
        List<Article> list = this.articleMapper.listRelevancy(id);
        if (list.size() <= 4) return list;
        Collections.shuffle(list);
        return list.subList(0, 4);
    }
}
