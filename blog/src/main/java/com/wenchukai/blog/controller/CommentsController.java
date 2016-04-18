package com.wenchukai.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wenchukai.blog.model.Comments;
import com.wenchukai.blog.model.PaginationResult;
import com.wenchukai.blog.service.CommentsService;
import com.wenchukai.common.base.BaseController;

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

	@RequestMapping(value = { "/list/{articleId}" })
	public PaginationResult<Comments> listComments(Integer page) {
		return null;
	}
}
