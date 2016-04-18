package com.wenchukai.blog.service;

import com.wenchukai.blog.dto.PageIn;
import com.wenchukai.blog.model.Comments;
import com.wenchukai.blog.model.PaginationResult;

public interface CommentsService {
	
	boolean postComments(Comments comments);

	PaginationResult<Comments> listComments(PageIn<Comments> pageIn);
}
