package pro.chukai.web.controller;

import pro.chukai.web.dto.out.SideBarInfo;
import pro.chukai.web.dto.out.SiteFooteInfo;
import pro.chukai.web.service.ArticleService;
import pro.chukai.web.service.ArticleTagService;
import pro.chukai.web.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.chukai.web.dto.out.SideBarInfo;
import pro.chukai.web.service.SiteService;

@Controller
@RequestMapping(value = {"/api"})
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

}
