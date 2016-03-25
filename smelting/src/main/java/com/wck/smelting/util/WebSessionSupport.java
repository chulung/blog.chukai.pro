package com.wck.smelting.util;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wck.smelting.bean.Player;
import com.wck.smelting.enumerate.AuthorityEnum;
import com.wck.cache.CCache;
import com.wck.durable.core.Session;

/**
 * web 会话 请求支持
 * 
 * @author ChuKai
 *
 */
@Component
public class WebSessionSupport {
	public final String SESSION_ID = "session_id_smelting";
	@Resource
	private CCache cCache;
	@Resource
	private Session session;

	/**
	 * 判断当前用户是否登陆
	 * 
	 * @return
	 */
	public boolean isSignIn() {
		return isSignIn(getCurSessionId());
	}

	/**
	 * 判断当前用户是否登陆
	 * 
	 * @param sessionId
	 *            cookie中的sessionId
	 * @return
	 */
	public boolean isSignIn(String sessionId) {
		if (sessionId == null) {
			return false;
		}
		Optional<Player> curPlayer = this.getCurPlayer();
		curPlayer.ifPresent(u -> {
			expandSignIn(sessionId, u);
		});
		return curPlayer.isPresent();
	}

	/**
	 * 从cookie中获取当前用户的sessionId
	 * 
	 * @return
	 */
	public String getCurSessionId() {
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (SESSION_ID.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 获取当前用户对象信息
	 * 
	 * @return
	 */
	public Optional<Player> getCurPlayer() {
		String sessionId = getCurSessionId();
		Player Player = null;
		if (sessionId != null) {
			Player = cCache.get(getSessionCacheKey(sessionId));
			if (Player == null) {
				// 缓存未登录则判断是否为记住登录信息的用户
				Player Player2 = new Player();
				Player2.setSessionId(sessionId);
				Player = session.queryOne(Player2);
				this.expandSignIn(sessionId, Player);
			}
		}
		return Optional.ofNullable(Player);
	}

	/**
	 * 获取当前用户名
	 * 
	 * @return
	 */
	public Optional<String> getCurPlayerName() {
		Optional<Player> curPlayer = this.getCurPlayer();
		return Optional.ofNullable(curPlayer.isPresent() ? curPlayer.get().getUserName() : null);
	}

	public Optional<Integer> getCurPlayerId() {
		Optional<Player> curPlayer = this.getCurPlayer();
		return Optional.ofNullable(curPlayer.isPresent() ? curPlayer.get().getId() : null);
	}

	/**
	 * 30分钟有效期
	 * 
	 * @param sessionId
	 * @param Player
	 */
	private void expandSignIn(String sessionId, Player Player) {
		cCache.put(getSessionCacheKey(sessionId), Player, 1800);
	}

	/**
	 * 将用户信息缓存,表示登陆,返回生成的sessionId
	 * 
	 * @param Player
	 * @return
	 */
	public String signIn(Player Player) {
		String gegenerateSessionId = gegenerateSessionId(Player);
		expandSignIn(gegenerateSessionId, Player);
		return gegenerateSessionId;
	}

	/**
	 * 通过uuid和PlayerId生成唯一sessionId
	 * 
	 * @param Player
	 * @return
	 */
	private String gegenerateSessionId(Player Player) {
		return UUID.randomUUID().toString() + Player.getId();
	}

	/**
	 * 拼接缓存的key
	 * 
	 * @param sessionId
	 * @return
	 */
	private String getSessionCacheKey(String sessionId) {
		return SESSION_ID + sessionId;
	}

	/**
	 * 注销,清空缓存及持久化的session
	 */
	public void logout() {
		String sessionId = getCurSessionId();
		if (sessionId == null) {
			return;
		}
		this.cCache.remove(getSessionCacheKey(sessionId));
		session.execute("update Player set sessionId=? where sessionId=?", "", sessionId);
	}

	public Integer getCurPlayerAuthority() {
		Optional<Player> curPlayer = this.getCurPlayer();
		return curPlayer.isPresent() ? curPlayer.get().getAuthority() : AuthorityEnum.VISITOR.getCode();
	}

}
