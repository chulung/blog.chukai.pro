package com.chulung.website.controller;

import com.chulung.search.ArticlesSearchHandler;
import com.chulung.website.dto.JsonResult;
import com.chulung.website.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController extends  BaseController{

    @Autowired
    private ArticlesSearchHandler articlesSearchHandler;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult<List<Article>> search(@RequestParam(defaultValue = "CSearch") String word) {
        if (word.length() > 20) word = word.substring(0, 20);
        return JsonResult.ofSuccess(this.articlesSearchHandler.search(word));
    }
}
