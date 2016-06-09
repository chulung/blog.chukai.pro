package com.chulung.blog.session;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import com.chulung.blog.common.util.NetUtil;
import com.chulung.blog.mapper.UserMapper;
import com.chulung.blog.model.User;

/**
 * web 会话 请求支持，用于登陆拦截
 * 
 * @author ChuKai
 *
 */
@Component
@CacheConfig(cacheNames = { "default" })
public class WebSessionSupport {
	public final String SESSION_ID = "session_id";
	@Resource
	private UserMapper userMapper;

	private ConcurrentMap<String, Object> cCache = new ConcurrentHashMap<String, Object>();

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
		return NetUtil.getCookieValue(SESSION_ID);
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
			user = (User) cCache.get(getSessionCacheKey(sessionId));
			if (user == null) {
				// 缓存未登录则判断是否为记住登录信息的用户
				user = new User();
				user.setSessionId(sessionId);
				user.setRememberLogin(1);
				user = userMapper.selectOne(user);
			}
		}
		return Optional.ofNullable(user);
	}

	/**
	 * 获取当前用户名
	 * 
	 * @re ``turn
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
		// cCache.put(getSessionCacheKey(sessionId), user, 1800);
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
		// this.cCache.remove(getSessionCacheKey(sessionId));
	}

}