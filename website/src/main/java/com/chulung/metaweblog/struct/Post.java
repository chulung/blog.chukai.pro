package com.chulung.metaweblog.struct;

import java.util.Arrays;
import java.util.Date;

/**
 * 博客文章结构体
 * @author chulung1
 *
 */
public final class Post extends Struct<Post> {


	/**
	 * 创建日期
	 */
	private Date dateCreated;
	/**
	 * 博客内容，建议直接推送html格式
	 */
	private String description;
	/**
	 * 博客标题
	 */
	private String title;
	private String[] categories;
	private Enclosure enclosure;
	private String link;
	/**
	 * 应该是目标网站的当前文章链接
	 */
	private String permalink;
	/**
	 * 目标网站的文章id，存在本地可用于编辑和删除对应网站的文章
	 */
	private String postid;
	private Source source;
	private String userid;
	private Integer mt_allow_comments;
	private Integer mt_allow_pings;
	private Integer mt_convert_breaks;
	private String mt_text_more;
	private String mt_excerpt;
	/**
	 * cnblogs 表示标签 ,分割 如 高并发,架构
	 */
	private String mt_keywords;
	private String wp_slug;
	public Post() {
	}
	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public Enclosure getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getMt_allow_comments() {
		return mt_allow_comments;
	}

	public void setMt_allow_comments(Integer mt_allow_comments) {
		this.mt_allow_comments = mt_allow_comments;
	}

	public Integer getMt_allow_pings() {
		return mt_allow_pings;
	}

	public void setMt_allow_pings(Integer mt_allow_pings) {
		this.mt_allow_pings = mt_allow_pings;
	}

	public Integer getMt_convert_breaks() {
		return mt_convert_breaks;
	}

	public void setMt_convert_breaks(Integer mt_convert_breaks) {
		this.mt_convert_breaks = mt_convert_breaks;
	}

	public String getMt_text_more() {
		return mt_text_more;
	}

	public void setMt_text_more(String mt_text_more) {
		this.mt_text_more = mt_text_more;
	}

	public String getMt_excerpt() {
		return mt_excerpt;
	}

	public void setMt_excerpt(String mt_excerpt) {
		this.mt_excerpt = mt_excerpt;
	}

	public String getMt_keywords() {
		return mt_keywords;
	}

	public void setMt_keywords(String mt_keywords) {
		this.mt_keywords = mt_keywords;
	}

	public String getWp_slug() {
		return wp_slug;
	}

	public void setWp_slug(String wp_slug) {
		this.wp_slug = wp_slug;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Post{" +
				"dateCreated=" + dateCreated +
				", description='" + description + '\'' +
				", title='" + title + '\'' +
				", categories=" + Arrays.toString(categories) +
				", enclosure=" + enclosure +
				", link='" + link + '\'' +
				", permalink='" + permalink + '\'' +
				", postid='" + postid + '\'' +
				", source=" + source +
				", userid='" + userid + '\'' +
				", mt_allow_comments=" + mt_allow_comments +
				", mt_allow_pings=" + mt_allow_pings +
				", mt_convert_breaks=" + mt_convert_breaks +
				", mt_text_more='" + mt_text_more + '\'' +
				", mt_excerpt='" + mt_excerpt + '\'' +
				", mt_keywords='" + mt_keywords + '\'' +
				", wp_slug='" + wp_slug + '\'' +
				'}';
	}
}
