package com.chulung.craft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chulung.craft.constant.Constants;
import com.chulung.craft.dto.PageIn;
import com.chulung.craft.model.Comments;
import com.chulung.craft.model.PaginationResult;
import com.chulung.craft.service.CommentsService;

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
