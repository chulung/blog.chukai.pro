package com.chulung.blog.dto;

import java.util.List;

/**
 * 页面公共信息
 * 
 * @author hasee
 *
 */
public class CommonInfo {
	/**
	 * 归档日期
	 */
	private List<ArticleFiling> articleFilings;

	/**
	 * 标签
	 */
	private List<String> tags;

	public CommonInfo() {
	}

	public CommonInfo(List<ArticleFiling> articleFilings) {
		this.articleFilings=articleFilings;
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

}
