package com.chulung.craft.service.impl;

import javax.annotation.Resource;

import com.chulung.craft.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.craft.model.User;
import com.chulung.craft.service.UserService;
import com.chulung.craft.session.WebSessionSupport;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Resource
	private WebSessionSupport webSessionSupport;
	@Autowired
	private UserMapper userMapper;

	@Override
	public User logInbackend(User user) {
		checkExistBlank(user.getUserName(), user.getPassword());
		user = userMapper.selectOne(user);
		if (user != null) {
			// 回写sessionId
			user.setSessionId(webSessionSupport.logIn(user));
			User bean = new User();
			bean.setId(user.getId());
			bean.setSessionId(user.getSessionId());
			userMapper.updateByPrimaryKeySelective(bean);
		}
		return user;
	}

}
