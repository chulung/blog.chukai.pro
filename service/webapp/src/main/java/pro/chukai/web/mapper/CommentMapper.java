package pro.chukai.web.mapper;

import pro.chukai.mybatis.mapper.BaseMapper;
import pro.chukai.web.model.Comment;
import pro.chukai.mybatis.mapper.BaseMapper;

public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据文章id更新评论数
     *
     * @param id
     */
    public void recalcCommentsCountForArticle(Integer id);
}