package com.chulung.website.controller;

import static com.chulung.website.dto.JsonResult.*;

import com.chulung.website.dto.out.SideBarInfo;
import com.chulung.website.dto.JsonResult;
import com.chulung.website.dto.out.SiteFooteInfo;
import com.chulung.website.model.Article;
import com.chulung.website.service.ArticleService;
import com.chulung.website.service.ArticleTagService;
import com.chulung.website.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = {"/"})
public class SiteFrontController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = {"/sidebarInfo"}, method = RequestMethod.GET)
    public
    @ResponseBody
    SideBarInfo sidebarInfo() {
        return this.siteService.getSideBarInfo();
    }

    @RequestMapping(value = "/siteFooterInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    SiteFooteInfo siteFooteInfo() {
        return this.siteService.getSiteFooteInfo();
    }


    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult<List<Article>> getArticlesByTag(@PathVariable String tagName) {
        return ofSuccess(this.articleService.getArticlesByTagName(tagName));
    }
}
