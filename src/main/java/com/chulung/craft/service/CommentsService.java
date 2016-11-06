package com.chulung.craft.service;

import com.chulung.craft.dto.PageIn;
import com.chulung.craft.model.Comments;
import com.chulung.craft.model.PaginationResult;

import java.util.List;

public interface CommentsService {
	
	boolean postComments(Comments comments);

	PaginationResult<Comments> listComments(PageIn<Comments> pageIn);

	List<Comments> listRecentlyComments();
}
