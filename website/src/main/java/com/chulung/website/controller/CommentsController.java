package com.chulung.website.controller;

import com.chulung.website.dto.out.CommentsOut;
import com.chulung.website.dto.out.PageOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.chulung.website.model.Comments;
import com.chulung.website.service.CommentsService;

@RequestMapping("/comments")
@RestController
public class CommentsController extends BaseController {
	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = { "" }, method = RequestMethod.POST)
	public ModelMap post(Comments comments) {
		return commentsService.postComments(comments) ? successMap() : errorMap();
	}

	@RequestMapping(value = { "/list/{articleId}" }, method = RequestMethod.GET)
	public @ResponseBody
	PageOut<CommentsOut> listComments(Integer page, @PathVariable Integer articleId) {
		return commentsService.listComments(page,articleId);
	}
}
