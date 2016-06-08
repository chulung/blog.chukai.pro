package com.chulung.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.mapper.UserMapper;
import com.chulung.blog.model.User;
import com.chulung.blog.service.UserService;
import com.chulung.blog.session.WebSessionSupport;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Resource
	private WebSessionSupport webSessionSupport;
	@Autowired
	private UserMapper userMapper;

	@Override
	public User signInbackend(User user) {
		checkExistBlank(user.getUserName(), user.getPassword());
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
