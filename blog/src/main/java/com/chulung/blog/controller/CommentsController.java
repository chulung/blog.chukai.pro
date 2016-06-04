package com.chulung.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chulung.blog.constant.Constants;
import com.chulung.blog.dto.PageIn;
import com.chulung.blog.model.Comments;
import com.chulung.blog.model.PaginationResult;
import com.chulung.blog.service.CommentsService;
import com.chulung.common.base.BaseController;

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
	public @ResponseBody PaginationResult<Comments> listComments(Integer page,@PathVariable Integer articleId) {
		PageIn<Comments> pageIn=new PageIn<Comments>(0, Constants.DEFAULT_PAGE_SIZE, new Comments(articleId));
		return commentsService.listComments(pageIn);
	}
}
