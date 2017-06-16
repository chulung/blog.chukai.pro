package com.chulung.website.controller;

import com.chulung.website.dto.out.CommentsOut;
import com.chulung.website.dto.out.PageOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chulung.website.model.Comment;
import com.chulung.website.service.CommentService;

@RequestMapping("/api/comments")
@RestController
public class CommentsController extends BaseController {
    @Autowired
    private CommentService commentsService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity post(@RequestBody Comment comments) {
        commentsService.postComments(comments);
        return success();
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public
    @ResponseBody
    PageOut<CommentsOut> get(Integer page, Integer articleId) {
        return commentsService.listComments(page, articleId);
    }

}
