package pro.chukai.web.controller;

import pro.chukai.web.dto.out.ArticleOut;
import pro.chukai.web.dto.out.PageOut;
import pro.chukai.web.model.Article;
import pro.chukai.web.service.ArticleService;
import pro.chukai.web.service.ColumnSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chukai
 */
@RestController
@RequestMapping("/api")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ColumnSevice columnSevice;

    @RequestMapping(value = "/article/{uri}", method = RequestMethod.GET)
    public
    @ResponseBody
    Article getArticleById(@PathVariable String uri) {
        return articleService.findArticleByUri(uri);
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

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public
    @ResponseBody
    PageOut<ArticleOut> getArticlesByTag(@RequestParam(required = false) String tag) {
        return this.articleService.getArticlesByTagName(tag);
    }
}
