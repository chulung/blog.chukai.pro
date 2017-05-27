package com.chulung.website.session;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import com.chulung.website.mapper.UserMapper;
import com.chulung.website.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
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
	 * 获取当前用户对象信息
	 * 
	 * @return
	 */
	public Optional<User> getCurUser() {
		String sessionId = NetUtil.getCurSessionId();
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
		String sessionId = NetUtil.getCurSessionId();
		this.cache.put(this.getSessionCacheKey(sessionId),user);
		return sessionId;
	}

	/**
	 * 拼接缓存的key
	 * 
	 * @param sessionId
	 * @return
	 */
	private String getSessionCacheKey(String sessionId) {
		return NetUtil.SESSION_ID + sessionId;
	}

	/**
	 * 注销,清空缓存及持久化的session
	 */
	public void logOut() {
		String sessionId = NetUtil.getCurSessionId();
		if (sessionId == null) {
			return;
		}
		this.cache.evict(getSessionCacheKey(sessionId));
	}
}
