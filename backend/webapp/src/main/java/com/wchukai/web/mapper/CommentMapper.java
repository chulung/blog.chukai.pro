package com.wchukai.web.mapper;

import com.wchukai.mybatis.mapper.BaseMapper;
import com.wchukai.web.model.Comment;

public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据文章id更新评论数
     *
     * @param id
     */
    public void recalcCommentsCountForArticle(Integer id);
}