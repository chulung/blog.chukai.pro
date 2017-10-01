package com.wchukai.web.service;

import com.wchukai.web.dto.out.CommentsOut;
import com.wchukai.web.dto.out.PageOut;
import com.wchukai.web.model.Comment;

import java.util.List;

public interface CommentService {

    boolean postComments(Comment comments);

    PageOut<CommentsOut> listComments(int pageNum, Integer articleId);

    List<Comment> findRecentlyComments();
}
