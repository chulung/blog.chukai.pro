package pro.chukai.web.service;

import pro.chukai.web.dto.out.CommentsOut;
import pro.chukai.web.dto.out.PageOut;
import pro.chukai.web.model.Comment;
import pro.chukai.web.model.Comment;

import java.util.List;

public interface CommentService {

    boolean postComments(Comment comments);

    PageOut<CommentsOut> listComments(int pageNum, Integer articleId);

    List<Comment> findRecentlyComments();
}
