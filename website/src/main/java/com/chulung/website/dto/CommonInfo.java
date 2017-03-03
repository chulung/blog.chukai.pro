package com.chulung.website.dto;

import java.io.Serializable;
import java.util.List;

import com.chulung.website.model.Article;
import com.chulung.website.model.ArticleTag;
import com.chulung.website.model.Comments;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 页面公共信息
 *
 * @author hasee
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonInfo implements Serializable {
    /**
     * 归档日期
     */
    private List<ArticleFiling> articleFilings;

    private List<ArticleTag> tags;

    private List<Comments> recentlyComments;

    private List<Article> popularArticles;

    private List<Article> recommendedArticles;

    public List<Article> getRecommendedArticles() {
        return recommendedArticles;
    }

    public void setRecommendedArticles(List<Article> recommendedArticles) {
        this.recommendedArticles = recommendedArticles;
    }

    public List<Comments> getRecentlyComments() {
        return recentlyComments;
    }

    public List<Article> getPopularArticles() {
        return popularArticles;
    }

    public void setPopularArticles(List<Article> popularArticles) {
        this.popularArticles = popularArticles;
    }

    public void setRecentlyComments(List<Comments> recentlyComments) {
        this.recentlyComments = recentlyComments;
    }

    public CommonInfo() {
    }

    public CommonInfo(List<ArticleFiling> articleFilings) {
        this.articleFilings = articleFilings;
    }

    public List<ArticleFiling> getArticleFilings() {
        return articleFilings;
    }

    public void setArticleFilings(List<ArticleFiling> articleFilings) {
        this.articleFilings = articleFilings;
    }

    public List<ArticleTag> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTag> tags) {
        this.tags = tags;
    }

}
