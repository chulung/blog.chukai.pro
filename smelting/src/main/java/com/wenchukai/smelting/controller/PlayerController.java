package com.wenchukai.smelting.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wenchukai.smelting.annotation.Authority;
import com.wenchukai.smelting.bean.Player;
import com.wenchukai.smelting.enumerate.AuthorityEnum;
import com.wenchukai.smelting.service.PlayerService;
import com.wenchukai.smelting.util.WebSessionSupport;

@RestController
@RequestMapping("/player")
public class PlayerController extends BaseController {
	@Resource
	private WebSessionSupport webSessionSupport;
	@Resource
	private PlayerService playerService;

	@Override
	public ModelAndView modelAndView(String pageName) {
		return super.modelAndView(pageName).addObject("pageName", "player");
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index() {
		return this.getPlayer(this.webSessionSupport.getCurPlayerId().get());
	}

	@RequestMapping("/player/{id}")
	public ModelAndView getPlayer(@PathVariable Integer id) {
		Player player = this.playerService.getPlayerById(id);
		return modelAndView("/player/player").addObject("player", player).addObject("isSelf",
				id.equals(this.webSessionSupport.getCurPlayerId().get()));
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
			return new ModelAndView("redirect:/smelting/player");
		}
		return modelAndView("/player/signIn");
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
	public ModelAndView signIn(@ModelAttribute Player player, HttpServletResponse response) {
		player = this.playerService.signIn(player);
		if (player == null) {
			return modelAndView("/player/signIn").addObject("player", player).addObject("error", true);
		}
		// 回写sessionId cookie
		Cookie cookie = new Cookie(webSessionSupport.SESSION_ID, player.getSessionId());
		cookie.setPath("/smelting");// cookie 必须设置为根路径,否则会导致其他子路径无法拿到cookie
		cookie.setMaxAge(864000);// 10天
		response.addCookie(cookie);
		return new ModelAndView("redirect:/player");
	}
}
