package com.chulung.blog.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chulung.blog.dto.CommonInfo;
import com.chulung.blog.model.Article;
import com.chulung.blog.service.ArticleService;
import com.chulung.blog.service.ChatterService;
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
	private ChatterService chatterService;

	/**
	 * 博客首页
	 * 
	 * @return
	 */
	// @Cache(key = "blog-index", timeToLive = 30)
	@RequestMapping(value = { "/", "/index.html", "" })
	public ModelAndView getIndex() {
		return getBlogPage(1, null);
	}

	/**
	 * 页面公共信息
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/sidebarInfo" }, method = RequestMethod.GET)
	public @ResponseBody CommonInfo getCommonInfo() {
		return this.articleService.getCommonInfo();
	}

	/**
	 * 按月查询文章
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping(value = "/monthFilings/{year}-{month}")
	public @ResponseBody ModelAndView getMonthFilings(@PathVariable Integer year, @PathVariable Integer month) {
		return modelAndView().addObject("blogs", articleService.getBlogsByYearMonth(year, month));
	}

	/**
	 * 博客文章分页
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/blog/page/{page}", method = RequestMethod.GET)
	public ModelAndView getBlogPage(@PathVariable Integer page, @RequestParam(required = false) Integer typeId) {
		Optional<Integer> ofNullable = Optional.ofNullable(page);
		PageInfo<Article> pageInfo = articleService.selectBySelectiveForBlog(ofNullable, typeId);
		return modelAndView().addObject("blogs", pageInfo.getList()).addObject("page", ofNullable.orElse(1))
				.addObject("prePage", page > 1 ? page - 1 : null)
				.addObject("nextPage", page < pageInfo.getPages() ? page + 1 : null).addObject("typeId", typeId);

	}

	@RequestMapping("/tech")
	public ModelAndView getArticles() {
		return getBlogPage(1, 1).addObject("headContext","技术改变世界~");
	}

	@RequestMapping("/reprints")
	public ModelAndView getReprint() {
		return getBlogPage(1, 3).addObject("headContext","他山之石可以攻玉~");
	}

	@RequestMapping("/about")
	public ModelAndView about() {
		Article article = articleService.findArticleById(20);
		double wook = (Instant.now().getEpochSecond() - Instant.parse("2015-03-01T09:00:00.00Z").getEpochSecond())
				/ 31536000.0;
		wook = new BigDecimal(wook).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		article.setContext( String.format(article.getContext(), wook));
		return modelAndView("article").addObject("article",article)
				.addObject("typeId", 4);
	}
}
