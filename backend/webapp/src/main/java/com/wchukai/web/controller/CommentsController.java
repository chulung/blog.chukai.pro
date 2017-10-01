package com.wchukai.web.controller;

import com.wchukai.web.dto.out.CommentsOut;
import com.wchukai.web.dto.out.PageOut;
import com.wchukai.web.model.Comment;
import com.wchukai.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    PageOut<CommentsOut> get(int page, Integer articleId) {
        return commentsService.listComments(page, articleId);
    }

}
