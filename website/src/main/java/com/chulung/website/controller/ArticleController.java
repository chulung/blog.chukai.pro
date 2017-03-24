package com.chulung.website.controller;

import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.exception.PageNotFoundException;
import com.chulung.website.model.Article;
import com.chulung.website.model.Column;
import com.chulung.website.service.ArticleService;
import com.chulung.website.service.ColumnSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chulung.website.dto.JsonResult.ofSuccess;

/**
 * @author chulung
 */
@RestController
@RequestMapping("/")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ColumnSevice columnTypeSevice;

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Article getArticleById(@PathVariable Integer id) {
        return articleService.findArticleById(id);
    }

    @RequestMapping(value = "/article/relevancy/{id}")
    public
    @ResponseBody
    List<Article> relevancy(@PathVariable Integer id) {
        return this.articleService.findRelevancyByArticleId(id);
    }

    /**
     * 博客文章分页
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public
    @ResponseBody
    PageOut<ArticleOut> getArticlePage(@RequestParam(required = false) Integer page, @RequestParam(required = false) String column, @RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        return articleService.findArticlePage(page, column, year, month);
    }

    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    public
    @ResponseBody
    PageOut<ArticleOut> getArticlesByTag(@PathVariable String tagName) {
        return this.articleService.getArticlesByTagName(tagName);
    }
}
