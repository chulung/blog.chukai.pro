package com.chulung.website.service.impl;

import javax.annotation.Resource;

import com.chulung.website.exception.HttpStatusException;
import com.chulung.website.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chulung.website.model.User;
import com.chulung.website.service.UserService;
import com.chulung.website.session.WebSessionSupport;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Resource
    private WebSessionSupport webSessionSupport;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User logInbackend(User in) {
        checkExistBlank(in.getUserName(), in.getPassword());
        User result = userMapper.selectOne(in);
        if (result != null) {
            // 回写sessionId
            result.setSessionId(webSessionSupport.logIn(result));
            User bean = new User();
            bean.setId(result.getId());
            bean.setSessionId(result.getSessionId());
            userMapper.updateByPrimaryKeySelective(bean);
        } else {
            throw HttpStatusException.of(HttpStatus.UNAUTHORIZED);
        }
        return result;
    }

}
