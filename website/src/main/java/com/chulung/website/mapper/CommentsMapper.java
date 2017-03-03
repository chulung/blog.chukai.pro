package com.chulung.website.mapper;

import com.chulung.website.model.Comments;
import com.chulung.mybatis.mapper.BaseMapper;

public interface CommentsMapper extends BaseMapper<Comments> {
	/**
	 * 根据文章id更新评论数
	 * @param id
	 */
	public void recalcCommentsCountForArticle(Integer id);
}