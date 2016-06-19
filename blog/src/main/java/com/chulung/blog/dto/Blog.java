package com.chulung.blog.dto;

import java.time.LocalDateTime;

import com.chulung.blog.model.Article;
import com.chulung.blog.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 公用的blog dto
 * 
 * @author ChuKai
 *
 */
public class Blog extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1248733421547044732L;
	private Integer id;
	private String author;
	private String summary;
	private String derivationUrl;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime createTime;
	private Integer typeId;
	private String title;
	private Integer visitCount;
	private Integer commentCount;


	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static Blog valueOf(Article article) {
		Blog blog=new Blog();
		blog.id=article.getId();
		blog.author=article.getAuthor();
		blog.summary=generatingSummary(article.getContext());
		blog.createTime=article.getCreateTime();
		blog.title=article.getTitle();
		blog.typeId=article.getTypeId();
		blog.commentCount=article.getCommentCount();
		blog.visitCount=article.getVisitCount();
		return blog;
	}

	private static String generatingSummary(String context) {
		String replaceAll = context.replaceAll("</?.*?>", "");
		return replaceAll.length() > 120 ? replaceAll.substring(0, 120) + "..." : replaceAll;
	}

	public String getDerivationUrl() {
		return derivationUrl;
	}

	public void setDerivationUrl(String derivationUrl) {
		this.derivationUrl = derivationUrl;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

}
