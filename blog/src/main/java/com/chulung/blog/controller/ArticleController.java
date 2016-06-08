package com.chulung.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chulung.blog.model.Article;
import com.chulung.blog.service.ArticleService;

/**
 * 基于rest风格的文章,草稿WS
 * 
 * @author ChuKai
 *
 */
@RestController
@RequestMapping("/")
public class ArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;

	/**
	 * 根据id查询文章
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getArticle(@PathVariable Integer id, HttpServletRequest request) {
		Article article = articleService.findArticleById(id);
		return modelAndView("article").addObject("article", article).addObject("typeId", article.getTypeId());
	}

}
