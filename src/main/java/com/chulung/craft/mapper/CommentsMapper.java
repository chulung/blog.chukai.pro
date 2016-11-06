package com.chulung.craft.mapper;

import com.chulung.craft.model.Comments;

public interface CommentsMapper extends BaseMapper<Comments>{
	/**
	 * 根据文章id更新评论数
	 * @param id
	 */
	public void recalcCommentsCountForArticle(Integer id);
}