package com.chulung.website.session;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import com.chulung.website.mapper.UserMapper;
import com.chulung.website.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.chulung.common.util.NetUtil;

/**
 * web 会话 请求支持，用于登陆拦截
 * 
 * @author chulung
 *
 */
@Component
public class WebSessionSupport{
	public final String SESSION_ID = "session_id";
	@Autowired
	private UserMapper userMapper;

	@Resource(name = "halfhour")
	private Cache cache;

	/**
	 * 判断当前用户是否登陆
	 * 
	 * @return
	 */
	public boolean islogIn() {
		return this.getCurUser().isPresent();
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
			user = cache.get(getSessionCacheKey(sessionId),User.class);
			if (user == null) {
				// 缓存未登录则判断数据库
				user = new User();
				user.setSessionId(sessionId);
				user = userMapper.selectOne(user);
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
	 * 将用户信息缓存,表示登陆,返回生成的sessionId
	 * 
	 * @param user
	 * @return
	 */
	public String logIn(User user) {
		String sessionId = gegenerateSessionId(user);
		this.cache.put(sessionId,user);
		return sessionId;
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
	public void logOut() {
		String sessionId = getCurSessionId();
		if (sessionId == null) {
			return;
		}
		this.cache.evict(getSessionCacheKey(sessionId));
	}
}
