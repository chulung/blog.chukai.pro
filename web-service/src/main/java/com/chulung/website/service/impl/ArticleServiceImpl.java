package com.chulung.website.service.impl;

import com.chulung.search.ArticlesSearchHandler;
import com.chulung.website.dto.out.Archive;
import com.chulung.website.dto.in.ArticleIn;
import com.chulung.website.dto.out.ArticleDraftOut;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.enumerate.ConfigKeyEnum;
import com.chulung.website.enumerate.IsDeleteEnum;
import com.chulung.website.enumerate.PublishStatusEnum;
import com.chulung.website.exception.HttpStatusException;
import com.chulung.website.mapper.ArticleDraftHistoryMapper;
import com.chulung.website.mapper.ArticleDraftMapper;
import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.mapper.ArticleTagMapper;
import com.chulung.website.model.*;
import com.chulung.website.service.*;
import com.chulung.website.session.WebSessionSupport;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Range;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    private CommentService commentService;

    @Autowired
    private MetaClBlogLogService metaClBlogLogService;

    @Autowired
    private ArticlesSearchHandler articlesSearchHandler;

    @Autowired
    private ColumnSevice columnSevice;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    public Article findArticleById(Integer id) {
        Article a = articleMapper.selectByPrimaryKey(id);
        if (a == null) {
            throw HttpStatusException.of(HttpStatus.NOT_FOUND);
        }
        if (id == 20) {
            double wook = (Instant.now().getEpochSecond() - Instant.parse("2015-03-01T09:00:00.00Z").getEpochSecond())
                    / 31536000.0;
            wook = new BigDecimal(wook).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            int pinyin = (int) ((Instant.now().getEpochSecond() - Instant.parse("1867-01-01T00:00:00.00Z").getEpochSecond())
                    / 31536000);
            a.setContent(String.format(a.getContent(), pinyin, wook));
        }
        if (a.getColumnId() == 1) {
            a.setContent(a.getContent() + (a.getColumnId() != 3 && StringUtils.isBlank(a.getLicense()) ? configService.getValueBykey(ConfigKeyEnum.ARTICLE_LICENSE, ConfigKeyEnum.ARTICLE_LICENSE.name()) : a.getLicense()));
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
            throw HttpStatusException.of(HttpStatus.INTERNAL_SERVER_ERROR, "修改草稿失败");
        }
        return true;
    }

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
            Article article = buildeFromDraft(articleDraft);
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
            if (StringUtils.isNotBlank(article.getTags())) {
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
                throw HttpStatusException.of(HttpStatus.INTERNAL_SERVER_ERROR, "插入草稿失败");
            }
            return articleDraft.getId();
        } catch (DuplicateKeyException e) {
            throw HttpStatusException.of(HttpStatus.CONFLICT, "文章已存在");
        }
    }

    @Override
    public ArticleDraftOut findArticleDraft(Integer id) {
        return id == null ? null : new ArticleDraftOut().buildFromModel(this.articleDraftMapper.selectByPrimaryKey(id));
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
            throw HttpStatusException.of(HttpStatus.NOT_FOUND, "草稿不存在,id=" + id);
        }
    }


    @Override
    public PageOut<ArticleOut> findArticlePage(Integer page, String column, Integer year, Integer month) {
        ArticleIn bean = new ArticleIn();
        bean.setIsDelete(IsDeleteEnum.N);
        if (column != null) {
            Column c = this.columnSevice.getEnNameColumnMap().get(column);
            if (c == null) throw HttpStatusException.of(HttpStatus.NOT_FOUND);
            bean.setColumnId(c.getId());
        }
        if (year != null && Range.atLeast(2014).contains(year)) {
            if (month != null && Range.open(1, 12).contains(month)) {
                bean.setCreateTimeStart(LocalDateTime.of(year, month, 1, 0, 0));
                bean.setCreateTimeEnd(LocalDateTime.of(year, month, 1, 0, 0).plus(1, ChronoUnit.MONTHS));
            } else {
                bean.setCreateTimeStart(LocalDateTime.of(year, 1, 1, 0, 0));
                bean.setCreateTimeEnd(LocalDateTime.of(year, 1, 1, 0, 0).plus(1, ChronoUnit.YEARS));
            }
        }
        PageHelper.startPage(page == null ? 1 : page, PAGE_SIZE);
        Page<Article> articles = (Page<Article>) articleMapper.selectSummarys(bean);
        PageOut<ArticleOut> pageOut = new PageOut<>(articles.getPageNum(), articles.getPages());
        pageOut.setList(articles.stream().map(a ->
                new ArticleOut().buildFromModel(a)
        ).collect(Collectors.toList()));
        return pageOut;
    }

    @Override
    public List<Archive> getArchive() {
        // 归档信息
        List<Archive> list = new ArrayList<>();
        ArticleIn bean = new ArticleIn();
        bean.setIsDelete(IsDeleteEnum.N);
        //按月统计文章数量
        articleMapper.selectSummarys(bean).parallelStream().map(Article::getCreateTime)
                .map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonthValue()))
                .collect(Collectors.groupingBy(yearMonth -> yearMonth, Collectors.counting())).forEach((k, v) -> list.add(new Archive(k, v.intValue())));
        list.sort((o1, o2) -> o2.compareTo(o1));
        return list;
    }

    @Override
    public List<ArticleOut> findRecommendedArticles() {
        String recommendedArticleIds = this.configService.getValueBykey(ConfigKeyEnum.RECOMMENDED_ARTICLE_IDS);
        if (StringUtils.isNotBlank(recommendedArticleIds)) {
            ArticleIn dto = new ArticleIn();
            dto.setIds(Arrays.asList(recommendedArticleIds.split(",")).stream().map(s -> {
                return Integer.valueOf(s);
            }).collect(Collectors.toList()));
            return this.articleMapper.selectSummarys(dto).stream().map(article -> new ArticleOut().buildFromModel(article)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public PageOut<ArticleDraftOut> findArticleDraftsList(Integer page, Integer columnId) {
        PageHelper.startPage(page, 20);
        ArticleDraft record = new ArticleDraft();
        record.setColumnId(columnId);
        Page<ArticleDraft> articleDrafts = (Page<ArticleDraft>) this.articleDraftMapper.selectTileList(record);
        PageOut<ArticleDraftOut> pageOut = new PageOut<>(articleDrafts.getPageNum(), articleDrafts.getPages());
        pageOut.setList(articleDrafts.stream().map(a -> {
                    ArticleDraftOut out = new ArticleDraftOut().buildFromModel(a);
                    out.setColumnName(this.columnSevice.getIdColumnMap().get(a.getColumnId()).getCnName());
                    return out;
                }
        ).collect(Collectors.toList()));
        return pageOut;
    }

    @Override
    public List<Article> findPopularArticles() {
        PageHelper.startPage(1, 4);
        return this.articleMapper.listPopularArticles();

    }

    @Override
    public PageOut<ArticleOut> getArticlesByTagName(String tagName) {
        ArticleTag record = new ArticleTag();
        record.setTagName(tagName);
        List<Integer> articleIds = this.articleTagMapper.select(record).stream().map(t -> {
            return t.getArticleId();
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(articleIds)) return new PageOut<ArticleOut>();
        ArticleIn art = new ArticleIn();
        art.setIds(articleIds);
        return new PageOut<>(this.articleMapper.selectSummarys(art).stream().map(article -> new ArticleOut().buildFromModel(article)).collect(Collectors.toList()));
    }

    @Override
    public List<Article> findRelevancyByArticleId(Integer id) {
        List<Article> list = this.articleMapper.listRelevancy(id);
        if (list.size() <= 4) return list;
        Collections.shuffle(list);
        return list.subList(0, 4);
    }

    public Article buildeFromDraft(ArticleDraft articleDraft) {
        Article article = new Article();
        article.setId(articleDraft.getArticleId());
        String htmlContent = articleDraft.getHtmlContent();
        Pattern p = Pattern.compile("(https:)?//(\\w+\\.)?chulung.com.+?(\\.\\w{3})");
        Matcher m = p.matcher(htmlContent);
        if (m.find()) {
            article.setPic(m.group());
        }
        article.setColumnName(this.columnSevice.getIdColumnMap().get(articleDraft.getColumnId()).getCnName());
        article.setSummary(generatingSummary(htmlContent));
        article.setContent(htmlContent);
        article.setUpdateTime(articleDraft.getUpdateTime());
        article.setAuthor(articleDraft.getAuthor());
        article.setIsDelete(articleDraft.getIsDelete());
        article.setTitle(articleDraft.getTitle());
        article.setCreateTime(LocalDateTime.now());
        article.setColumnId(articleDraft.getColumnId());
        article.setVersion(articleDraft.getVersion());
        article.setTags(articleDraft.getTags());
        return article;
    }

    public String generatingSummary(String content) {
        String replaceAll = content.replaceFirst("<h[1-9](.+)?</h[1-9]>", "").replaceAll("</?.*?>", "");
        return replaceAll.length() > 100 ? replaceAll.substring(0, 97) + "..." : replaceAll;
    }


}
