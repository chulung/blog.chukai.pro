package com.chulung.website.service;

import com.chulung.website.dto.out.CommentsOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.model.Comment;

import java.util.List;

public interface CommentService {
	
	boolean postComments(Comment comments);

	PageOut<CommentsOut> listComments(int pageNum, int articleId);

	List<Comment> findRecentlyComments();
}
