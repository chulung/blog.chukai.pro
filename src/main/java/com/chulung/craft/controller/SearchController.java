package com.chulung.craft.controller;

import com.chulung.search.ArticlesSearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ChuKai on 2016/11/12.
 */
@RestController
@RequestMapping("/search")
public class SearchController extends  BaseController{

    @Autowired
    private ArticlesSearchHandler articlesSearchHandler;
    @RequestMapping("")
    public
    @ResponseBody
    ModelAndView search(@RequestParam(defaultValue = "CSearch") String word) {
        if (word.length() > 20) word = word.substring(0, 20);
        return  modelAndView().addObject("articles",this.articlesSearchHandler.search(word)).addObject("word",word);
    }
}
