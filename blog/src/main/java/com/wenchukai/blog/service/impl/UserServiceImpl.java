package com.wenchukai.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenchukai.blog.bean.User;
import com.wenchukai.blog.service.UserService;
import com.wenchukai.blog.util.WebSessionSupport;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Resource
	private WebSessionSupport webSessionSupport;

	@Override
	public User signInAdmin(User user) {
		if (checkExistBlank(user) && checkExistBlank(user.getUserName(), user.getPassword())) {
			return null;
		}
		User queryOne = session.queryOne(new User(user.getUserName(), user.getPassword(), 0));
		if (queryOne != null) {
			// 回写sessionId
			queryOne.setSessionId(webSessionSupport.signIn(queryOne));
			User bean = new User();
			bean.setId(queryOne.getId());
			bean.setSessionId(queryOne.getSessionId());
			bean.setRememberLogin(user.getRememberLogin());
			session.update(bean);
		}
		return queryOne;
	}

}
