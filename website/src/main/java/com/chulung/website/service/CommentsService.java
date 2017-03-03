package com.chulung.website.service;

import com.chulung.website.dto.PageIn;
import com.chulung.website.model.Comments;
import com.chulung.website.model.PaginationResult;

import java.util.List;

public interface CommentsService {
	
	boolean postComments(Comments comments);

	PaginationResult<Comments> listComments(PageIn<Comments> pageIn);

	List<Comments> listRecentlyComments();
}
