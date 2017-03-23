package com.chulung.website.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.chulung.website.service.ColumnSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.chulung.website.dto.JsonResult;
import com.chulung.website.dto.PageIn;
import com.chulung.website.model.ArticleDraft;
import com.chulung.website.model.User;
import com.chulung.website.service.ArticleService;
import com.chulung.website.service.UserService;
import com.chulung.website.session.WebSessionSupport;

/**
 * backend后台
 * 
 * @author chulung
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
	private ColumnSevice columnTypeSevice;


	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
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
		return modelAndView("/backend/logIn", "logIn");
	}

	/**
	 * 登录操作
	 * 
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/logIn" }, method = RequestMethod.POST)
	public ModelAndView logIn(@RequestBody User user, @RequestParam(required = false) String reUrl,
			HttpServletResponse response) {
		User backend = userService.logInbackend(user);
		if (backend == null) {
			return modelAndView("/backend/logIn", "logIn").addObject("user", user);
		}
		// 回写sessionId cookie
		Cookie cookie = new Cookie(webSessionSupport.SESSION_ID, backend.getSessionId());
		cookie.setPath("/");// cookie 必须设置为根路径,否则会导致其他子路径无法拿到cookie
		cookie.setMaxAge(600000);
		response.addCookie(cookie);
		return new ModelAndView("redirect:" + (reUrl != null ? reUrl : "/backend"));
	}

	/**
	 * 后台文章编辑器页
	 * 
	 * @return
	 */
	@RequestMapping("/cikiEditor")
	public ModelAndView cikiEditor() {
		return modelAndView("/backend/cikiEditor", "cikiEditor");
	}

	/**
	 * cikiEditor
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/articleEditor")
	public ModelAndView articleEditor(@RequestParam(required = false) Integer id) {
		return modelAndView("/backend/articleEditor", "articleEditor")
				.addObject("articleDraftId", id)
				.addObject("articleTypes",this.columnTypeSevice.getAllColumns());
	}

	/**
	 * 草稿列表
	 * 
	 * @return
	 */
	@RequestMapping("/articleDrafts")
	public ModelAndView articlesDrafts() {
		return modelAndView("/backend/articleDrafts", "articlesDrafts");
	}

	/**
	 * 草稿列表
	 * 
	 * @return
	 */
	@RequestMapping("/articleDrafts/list")
	public JsonResult<List<ArticleDraft>> getArticlesDrafts(@RequestBody PageIn<ArticleDraft> pageIn) {
		return JsonResult.ofSuccess(this.articleService.findArticleDraftsList(pageIn));
	}

	/**
	 * 根据id查询草稿
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/articleDraft/{id}", method = RequestMethod.GET)
	public @ResponseBody  JsonResult<ArticleDraft> getArticleDraft(@PathVariable Integer id) {
		return JsonResult.ofSuccess(this.articleService.findArticleDraft(id));
	}

	/**
	 * 保存修改草稿
	 * 
	 * @param articleDraft
	 * @return
	 */
	@RequestMapping(value = "/articleDraft", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> putArticleDraft(@RequestBody ArticleDraft articleDraft) {
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
	public @ResponseBody Map<String, Object> postArticle(@RequestBody ArticleDraft articleDraft) {
		return successMap().addAttribute("id", this.articleService.insert(articleDraft));
	}

	/**
	 * 删除草稿
	 * 
	 * @return
	 */
	@RequestMapping(value = "/articleDraft/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteArticleDraft(@PathVariable Integer id) {
		this.articleService.deleteArticleDraft(id);
		return successMap();
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

}
