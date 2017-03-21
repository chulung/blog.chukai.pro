package com.chulung.website.service;

import com.chulung.website.dto.in.PageIn;
import com.chulung.website.dto.out.CommentsOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.model.Comments;
import com.chulung.website.model.PaginationResult;

import java.util.List;

public interface CommentsService {
	
	boolean postComments(Comments comments);

	PageOut<CommentsOut> listComments(int pageNum, int articleId);

	List<Comments> findRecentlyComments();
}
