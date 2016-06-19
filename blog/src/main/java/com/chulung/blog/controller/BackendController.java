package com.chulung.blog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chulung.blog.dto.PageIn;
import com.chulung.blog.model.ArticleDraft;
import com.chulung.blog.model.Category;
import com.chulung.blog.model.User;
import com.chulung.blog.service.ArticleService;
import com.chulung.blog.service.CategoryService;
import com.chulung.blog.service.UserService;
import com.chulung.blog.session.WebSessionSupport;

/**
 * backend后台
 * 
 * @author ChuKai
 *
 */
@RestController
@RequestMapping("/backend")
public class BackendController extends BaseController {
	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;
	@Autowired
	private WebSessionSupport webSessionSupport;
	@Autowired
	private CategoryService categoryService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index() {
		return modelAndView("/backend/index");
	}

	/**
	 * 登录页,已登录则跳转首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/logIn" }, method = RequestMethod.GET)
	public ModelAndView logIn() {
		if (webSessionSupport.islogIn()) {
			return new ModelAndView("redirect:/backend");
		}
		return modelAndView("/backend/logIn");
	}

	/**
	 * 登录操作
	 * 
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/logIn" }, method = RequestMethod.POST)
	public ModelAndView logIn(@ModelAttribute User user, HttpServletResponse response) {
		User backend = userService.logInbackend(user);
		if (backend == null) {
			return modelAndView("/backend/logIn").addObject("user", user);
		}
		// 回写sessionId cookie
		Cookie cookie = new Cookie(webSessionSupport.SESSION_ID, backend.getSessionId());
		cookie.setPath("/");// cookie 必须设置为根路径,否则会导致其他子路径无法拿到cookie
		response.addCookie(cookie);
		return new ModelAndView("redirect:/backend");
	}

	/**
	 * 后台文章编辑器页
	 * 
	 * @param article
	 * @return
	 */
	@RequestMapping("/editors")
	public ModelAndView editors(@ModelAttribute ArticleDraft articleDraft) {
		return modelAndView("/backend/editors").addObject("articleTypes", articleService.findAllArticleTypes())
				.addObject("articleDraftId", articleDraft.getId() == null
						? articleService.findArticleDraftIdByArticleId(articleDraft) : articleDraft.getId());
	}

	/**
	 * 草稿列表
	 * 
	 * @return
	 */
	@RequestMapping("/articleDrafts")
	public ModelAndView articlesDrafts() {
		return modelAndView("/backend/articleDrafts");
	}

	/**
	 * 根据id查询草稿
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/articleDraft/{id}", method = RequestMethod.GET)
	public @ResponseBody ArticleDraft getArticleDraft(@PathVariable Integer id) {
		return this.articleService.findArticleDraft(id);
	}

	/**
	 * 保存修改草稿
	 * 
	 * @param articleDraft
	 * @return
	 */
	@RequestMapping(value = "/articleDraft", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> putArticleDraft(@ModelAttribute ArticleDraft articleDraft) {
		this.articleService.update(articleDraft);
		return successMap();
	}

	/**
	 * 保存新建草稿
	 * 
	 * @param articleDraft
	 * @return
	 */
	@RequestMapping(value = "/articleDraft", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postArticle(@ModelAttribute ArticleDraft articleDraft) {
		this.articleService.insert(articleDraft);
		return successMap();
	}

	/**
	 * 删除草稿
	 * 
	 * @param articleDraft
	 * @return
	 */
	@RequestMapping(value = "/articleDraft/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteArticleDraft(@PathVariable Integer id) {
		this.articleService.deleteArticleDraft(id);
		return successMap();
	}

	/**
	 * 分页查询草稿
	 * 
	 * @param pageIn
	 * @return
	 */
	@RequestMapping(value = "/articleDrafts/list", method = RequestMethod.GET)
	public @ResponseBody List<ArticleDraft> getArticleDrafts(@ModelAttribute PageIn<ArticleDraft> pageIn) {
		return articleService.findArticleDraftsList(pageIn);
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	@RequestMapping("/logOut")
	public ModelAndView logout(HttpServletResponse response) {
		webSessionSupport.logOut();
		Cookie cookie = new Cookie(webSessionSupport.SESSION_ID, "");
		cookie.setPath("/");// cookie 必须设置为根路径,否则会导致其他子路径无法拿到cookie
		response.addCookie(cookie);
		return modelAndView("/backend/logIn");
	}

	@RequestMapping("/ciki/category/list")
	public @ResponseBody List<Category> listCategory(@RequestParam Integer parentId) {
		return this.categoryService.getCategoryListByParentId(parentId);
	}
}
