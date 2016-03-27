package com.wck.blog.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wck.blog.annotation.Authority;
import com.wck.blog.bean.ArticleDraft;
import com.wck.blog.bean.User;
import com.wck.blog.enumerate.AuthorityEnum;
import com.wck.blog.service.ArticleService;
import com.wck.blog.service.UserService;

/**
 * admin后台
 * 
 * @author ChuKai
 *
 */
@RestController
@RequestMapping("/admin")
@Authority
public class AdminController extends BaseController {
	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index() {
		return modelAndView("/admin/index");
	}

	/**
	 * 登录页,已登录则跳转首页
	 * 
	 * @return
	 */
	@Authority(authority = AuthorityEnum.VISITOR)
	@RequestMapping(value = { "/signIn" }, method = RequestMethod.GET)
	public ModelAndView signIn() {
		if (webSessionSupport.isSignIn()) {
			return new ModelAndView("redirect:/admin");
		}
		return modelAndView("/admin/signIn");
	}

	/**
	 * 登录操作
	 * 
	 * @param user
	 * @param response
	 * @return
	 */
	@Authority(authority = AuthorityEnum.VISITOR)
	@RequestMapping(value = { "/signIn" }, method = RequestMethod.POST)
	public ModelAndView signIn(@ModelAttribute User user, HttpServletResponse response) {
		User admin = userService.signInAdmin(user);
		if (admin == null) {
			return modelAndView("/admin/signIn").addObject("user", user);
		}
		// 回写sessionId cookie
		Cookie cookie = new Cookie(webSessionSupport.SESSION_ID, admin.getSessionId());
		cookie.setPath("/");// cookie 必须设置为根路径,否则会导致其他子路径无法拿到cookie
		response.addCookie(cookie);
		return new ModelAndView("redirect:/admin");
	}

	/**
	 * 后台文章编辑器页
	 * 
	 * @param article
	 * @return
	 */
	@RequestMapping("/editors")
	public ModelAndView editors(@ModelAttribute ArticleDraft articleDraft) {
		return modelAndView("/admin/editors").addObject("articleTypes", articleService.findAllArticleTypes())
				.addObject("articleDraftId", articleDraft.getId() == null
						? articleService.findArticleDraftIdByArticleId(articleDraft) : articleDraft.getId());
	}

	/**
	 * 文章列表
	 * 
	 * @return
	 */
	@RequestMapping("/articles")
	public ModelAndView articles() {
		return modelAndView("/admin/articles");
	}

	/**
	 * 草稿列表
	 * 
	 * @return
	 */
	@RequestMapping("/articleDrafts")
	public ModelAndView articlesDrafts() {
		return modelAndView("/admin/articleDrafts");
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout() {
		webSessionSupport.logout();
		return modelAndView("/admin/signIn");
	}
}
