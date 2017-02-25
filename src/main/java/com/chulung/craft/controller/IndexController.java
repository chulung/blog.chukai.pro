package com.chulung.craft.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import com.chulung.craft.dto.CommonInfo;
import com.chulung.craft.model.Article;
import com.chulung.craft.model.ColumnType;
import com.chulung.craft.service.ArticleService;
import com.chulung.craft.service.ColumnTypeSevice;
import com.chulung.freemaker.template.EncodeURLMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

/**
 * 首页Controller
 * 
 * @author ChuKai
 *
 */
@Controller
@RequestMapping(value = { "/" })
public class IndexController extends BaseController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private ColumnTypeSevice columnTypeSevice;
	/**
	 * 博客首页
	 * 
	 * @return
	 */
	// @Cache(key = "Article-index", timeToLive = 30)
	@RequestMapping(value = { "/", "/index.html", "" }, method = RequestMethod.GET)
	public ModelAndView getIndex() {
		return getArticlePage(1, null);
	}

	/**
	 * 页面公共信息
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/sidebarInfo" }, method = RequestMethod.GET)
	public @ResponseBody
    CommonInfo getCommonInfo() {
		return this.articleService.getCommonInfo();
	}

	/**
	 * 按月查询文章
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/monthFilings/{year}-{month}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getMonthFilings(@PathVariable Integer year, @PathVariable Integer month) {
		return modelAndView().addObject("articles", articleService.getArticlesByYearMonth(year, month));
	}

	/**
	 * 博客文章分页
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ModelAndView getArticlePage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer typeId) {
		Optional<Integer> ofNullable = Optional.ofNullable(page);
		PageInfo<Article> pageInfo = articleService.selectBySelectiveForArticle(ofNullable, typeId);
		page=ofNullable.orElse(1);
		return modelAndView().addObject("articles", pageInfo.getList()).addObject("page", ofNullable.orElse(1))
				.addObject("prePage", page > 1 ? page - 1 : null)
				.addObject("nextPage", page < pageInfo.getPages() ? page + 1 : null).addObject("column", this.columnTypeSevice.getIdColumnMap().get(typeId));

	}

	/**
	 * 按栏目名显示文章列表
	 * @return
	 */
	@RequestMapping(value = "column/{columnType}", method = RequestMethod.GET)
	public ModelAndView getArticles(@PathVariable String columnType) {
		ColumnType c=this.columnTypeSevice.getEnNameColumnMap().get(columnType);
		if (c==null) return new ModelAndView("redirect:/");
		return getArticlePage(1, c.getId()).addObject("column",c);
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		Article article = articleService.findArticleById(20);

		return modelAndView("article").addObject("article",article);
	}

	@RequestMapping(value = "/tag/{tagName}",method = RequestMethod.GET)
	public ModelAndView getArticlesByTag(@PathVariable String tagName){
		return modelAndView().addObject("articles",this.articleService.getArticlesByTagName(tagName));
	}
}
