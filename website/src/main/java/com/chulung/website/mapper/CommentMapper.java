package com.chulung.website.mapper;

import com.chulung.website.model.Comment;
import com.chulung.mybatis.mapper.BaseMapper;

public interface CommentMapper extends BaseMapper<Comment> {
	/**
	 * 根据文章id更新评论数
	 * @param id
	 */
	public void recalcCommentsCountForArticle(Integer id);
}