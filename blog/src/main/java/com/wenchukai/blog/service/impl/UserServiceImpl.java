package com.wenchukai.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenchukai.blog.mapper.UserMapper;
import com.wenchukai.blog.model.User;
import com.wenchukai.blog.service.UserService;
import com.wenchukai.blog.util.WebSessionSupport;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Resource
	private WebSessionSupport webSessionSupport;
	@Autowired
	private UserMapper userMapper;

	@Override
	public User signInAdmin(User user) {
		if (checkExistBlank(user) && checkExistBlank(user.getUserName(), user.getPassword())) {
			return null;
		}
		user = userMapper.selectOne(user);
		if (user != null) {
			// 回写sessionId
			user.setSessionId(webSessionSupport.signIn(user));
			User bean = new User();
			bean.setId(user.getId());
			bean.setSessionId(user.getSessionId());
			bean.setRememberLogin(user.getRememberLogin());
			userMapper.updateByPrimaryKeySelective(bean);
		}
		return user;
	}

}
