package com.wchukai.web.controller;

import com.wchukai.search.ArticlesSearchHandler;
import com.wchukai.web.dto.out.ArticleOut;
import com.wchukai.web.dto.out.PageOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController extends BaseController {

    @Autowired
    private ArticlesSearchHandler articlesSearchHandler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    PageOut<ArticleOut> search(@RequestParam(defaultValue = "CSearch") String word) {
        if (word.length() > 20) word = word.substring(0, 20);
        return this.articlesSearchHandler.search(word);
    }
}
