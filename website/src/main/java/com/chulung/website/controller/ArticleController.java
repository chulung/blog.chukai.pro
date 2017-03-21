package com.chulung.website.controller;

import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.exception.PageNotFoundException;
import com.chulung.website.model.Article;
import com.chulung.website.model.ColumnType;
import com.chulung.website.service.ArticleService;
import com.chulung.website.service.ColumnTypeSevice;
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
    private ColumnTypeSevice columnTypeSevice;

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
    PageOut<ArticleOut> getArticlePage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer typeId) {
        return articleService.findArticlePage(page, typeId);
    }

    /**
     * 按栏目名显示文章列表
     *
     * @return
     */
    @RequestMapping(value = "column/{columnType}", method = RequestMethod.GET)
    public
    @ResponseBody
    PageOut<ArticleOut> getArticles(@PathVariable String columnType) {
        ColumnType c = this.columnTypeSevice.getEnNameColumnMap().get(columnType);
        if (c == null) throw new PageNotFoundException();
        return getArticlePage(1, c.getId());
    }

    @RequestMapping(value = "/monthFilings/{year}-{month}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Article> getMonthFilings(@PathVariable Integer year, @PathVariable Integer month) {
        return articleService.getArticlesByYearMonth(year, month);
    }
}
