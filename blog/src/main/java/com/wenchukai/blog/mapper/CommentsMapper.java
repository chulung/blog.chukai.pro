package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.Comments;
import com.wenchukai.ckbatis.mapper.BaseMapper;

public interface CommentsMapper extends BaseMapper<Comments>{
	/**
	 * 根据文章id更新评论数
	 * @param id
	 */
	public void recalcCommentsCountForArticle(Integer id);
}