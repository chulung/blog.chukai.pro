package com.wck.blog.controller;

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

import com.wck.blog.bean.Article;
import com.wck.blog.dto.CommonInfo;
import com.wck.blog.service.ArticleService;
import com.wck.blog.service.BlogService;
import com.wck.cache.annotation.Cache;

/**
 * 博客首页Controller
 * 
 * @author ChuKai
 *
 */
@Controller
@RequestMapping(value = { "/" })
public class BlogController extends BaseController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 博客首页
	 * 
	 * @return
	 */
	@Cache(key = "blog-index", timeToLive = 30)
	@RequestMapping(value = { "/", "/index.html", "/blog" }, method = RequestMethod.GET)
	public ModelAndView getIndex() {
		return getBlogPage(1, null);
	}

	/**
	 * 页面公共信息
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/commonInfo" }, method = RequestMethod.GET)
	public @ResponseBody CommonInfo getCommonInfo() {
		return this.blogService.getCommonInfo();
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
		return modelAndView("index").addObject("blogs", blogService.getBlogsByYearMonth(year, month));
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
		int pageCount = blogService.getBlogPageCount(typeId);
		return modelAndView("index").addObject("blogs", blogService.findNewBlog(ofNullable, typeId))
				.addObject("page", ofNullable.orElse(1)).addObject("prePage", page > 1 ? page - 1 : null)
				.addObject("nextPage", page < pageCount ? page + 1 : null).addObject("typeId", typeId);

	}

	@RequestMapping("/articles.html")
	public ModelAndView getArticles() {
		return getBlogPage(1, 1);
	}

	@RequestMapping("/essaies.html")
	public ModelAndView getEssaies() {
		return getBlogPage(1, 2);
	}

	@RequestMapping("/reprint.html")
	public ModelAndView getReprint() {
		return getBlogPage(1, 3);
	}

	@RequestMapping("/about")
	public ModelAndView about() {
		Article article = articleService.findArticleById(20);
		return modelAndView("about")
				.addObject("article", String.format(article.getContext(),
						String.valueOf((Instant.now().getEpochSecond()
								- Instant.parse("2015-06-15T09:00:00.00Z").getEpochSecond()) / 31536000.0),
						"魔都"))
				.addObject("typeId", 4);
	}
}
