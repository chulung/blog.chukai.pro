package com.chulung.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chulung.website.constant.Constants;
import com.chulung.website.dto.PageIn;
import com.chulung.website.model.Comments;
import com.chulung.website.model.PaginationResult;
import com.chulung.website.service.CommentsService;

/**
 * 评论
 * 
 * @author ChuKai
 *
 */
@RequestMapping("/comments")
@RestController
public class CommentsController extends BaseController {
	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = { "" }, method = RequestMethod.POST)
	public ModelMap post(@ModelAttribute Comments comments) {
		return commentsService.postComments(comments) ? successMap() : errorMap();
	}

	@RequestMapping(value = { "/list/{articleId}" }, method = RequestMethod.GET)
	public @ResponseBody PaginationResult<Comments> listComments(Integer page,@PathVariable Integer articleId) {
		PageIn<Comments> pageIn=new PageIn<Comments>(0, Constants.DEFAULT_PAGE_SIZE, new Comments(articleId));
		return commentsService.listComments(pageIn);
	}
}
