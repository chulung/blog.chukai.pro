package com.chulung.blog.dto;

import java.io.Serializable;
import java.util.List;

import com.chulung.blog.model.Article;
import com.chulung.blog.model.Chatter;
import com.chulung.blog.model.Comments;

/**
 * 页面公共信息
 *
 * @author hasee
 *
 */
public class CommonInfo implements Serializable{
	/**
	 * 归档日期
	 */
	private List<ArticleFiling> articleFilings;

	/**
	 * 标签
	 */
	private List<String> tags;

	private List<Comments> recentlyComments;

	private List<Article> popularArticles;

	private Chatter chatter;

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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Chatter getChatter() {
		return chatter;
	}

	public void setChatter(Chatter chatter) {
		this.chatter = chatter;
	}

}
