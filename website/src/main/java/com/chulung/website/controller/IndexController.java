package com.chulung.website.controller;

import static com.chulung.website.dto.JsonResult.*;

import com.chulung.website.dto.out.CommonInfo;
import com.chulung.website.dto.JsonResult;
import com.chulung.website.dto.out.ArticleOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.model.Article;
import com.chulung.website.model.ColumnType;
import com.chulung.website.service.ArticleService;
import com.chulung.website.service.ColumnTypeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页Controller
 *
 * @author ChuKai
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController extends BaseController {
    @Autowired
    private ArticleService articleService;


    /**
     * 页面公共信息
     *
     * @return
     */
    @RequestMapping(value = {"/sidebarInfo"}, method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult<CommonInfo> getCommonInfo() {
        return ofSuccess(this.articleService.getCommonInfo());
    }

    /**
     * 按月查询文章
     *
     * @param year
     * @param month
     * @return
     */
    @RequestMapping(value = "/monthFilings/{year}-{month}", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult<List<Article>> getMonthFilings(@PathVariable Integer year, @PathVariable Integer month) {
        return ofSuccess(articleService.getArticlesByYearMonth(year, month));
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public @ResponseBody  JsonResult<Article> about() {
        return ofSuccess(articleService.findArticleById(20));
    }

    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    public @ResponseBody  JsonResult<List<Article>> getArticlesByTag(@PathVariable String tagName) {
        return ofSuccess(this.articleService.getArticlesByTagName(tagName));
    }
}
