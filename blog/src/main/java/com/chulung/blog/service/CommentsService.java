package com.chulung.blog.service;

import com.chulung.blog.dto.PageIn;
import com.chulung.blog.model.Comments;
import com.chulung.blog.model.PaginationResult;

public interface CommentsService {
	
	boolean postComments(Comments comments);

	PaginationResult<Comments> listComments(PageIn<Comments> pageIn);
}
