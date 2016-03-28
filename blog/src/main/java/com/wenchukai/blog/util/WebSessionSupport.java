package com.wenchukai.blog.util;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wenchukai.blog.bean.User;
import com.wenchukai.blog.enumerate.AuthorityEnum;
import com.wenchukai.cache.CCache;
import com.wenchukai.durable.core.Session;

/**
 * web 会话 请求支持
 * 
 * @author ChuKai
 *
 */
@Component
public class WebSessionSupport {
	public final String SESSION_ID = "session_id";
	@Resource
	private CCache cCache;
	@Resource
	private Session session;

	/**
	 * 判断当前用户是否登陆
	 * 
	 * @param sessionId
	 *            cookie中的sessionId
	 * @return
	 */
	public boolean isSignIn() {
		Optional<User> curUser = this.getCurUser();
		curUser.ifPresent(u -> {
			expandSignIn(this.getCurSessionId(), u);
		});
		return curUser.isPresent();
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
	public Optional<User> getCurUser() {
		String sessionId = getCurSessionId();
		User user = null;
		if (sessionId != null) {
			user = cCache.get(getSessionCacheKey(sessionId));
			if (user == null) {
				// 缓存未登录则判断是否为记住登录信息的用户
				User user2 = new User();
				user2.setSessionId(sessionId);
				user2.setRememberLogin(1);
				user = session.queryOne(user2);
			}
		}
		return Optional.ofNullable(user);
	}

	/**
	 * 获取当前用户名
	 * 
	 * @return
	 */
	public Optional<String> getCurUserName() {
		Optional<User> curUser = this.getCurUser();
		return Optional.ofNullable(curUser.isPresent() ? curUser.get().getUserName() : null);
	}

	public Optional<Integer> getCurUserId() {
		Optional<User> curUser = this.getCurUser();
		return Optional.ofNullable(curUser.isPresent() ? curUser.get().getId() : null);
	}

	/**
	 * 用户每次请求重新刷新30分钟登陆有效期
	 * 
	 * @param sessionId
	 * 
	 * @param sessionId
	 * @param user
	 */
	private void expandSignIn(String sessionId, User user) {
		cCache.put(getSessionCacheKey(sessionId), user, 1800);
	}

	/**
	 * 将用户信息缓存,表示登陆,返回生成的sessionId
	 * 
	 * @param user
	 * @return
	 */
	public String signIn(User user) {
		String gegenerateSessionId = gegenerateSessionId(user);
		expandSignIn(gegenerateSessionId, user);
		return gegenerateSessionId;
	}

	/**
	 * 通过uuid和userId生成唯一sessionId
	 * 
	 * @param user
	 * @return
	 */
	private String gegenerateSessionId(User user) {
		return UUID.randomUUID().toString() + user.getId();
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
		session.execute("update user set sessionId=? and rememberLogin=? where sessionId=?", "", 0, sessionId);
	}

	public Integer getCurUserAuthority() {
		Optional<User> curUser = this.getCurUser();
		return curUser.isPresent() ? curUser.get().getAuthority() : AuthorityEnum.VISITOR.getCode();
	}

}
