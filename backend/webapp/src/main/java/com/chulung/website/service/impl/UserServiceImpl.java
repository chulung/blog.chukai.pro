package com.chulung.website.service.impl;

import com.chulung.common.util.MessageDigestUtil;
import com.chulung.common.util.NetUtil;
import com.chulung.website.dto.in.UserIn;
import com.chulung.website.exception.HttpStatusException;
import com.chulung.website.mapper.UserMapper;
import com.chulung.website.model.User;
import com.chulung.website.service.UserService;
import com.chulung.website.session.WebSessionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Resource
    private WebSessionSupport webSessionSupport;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User logInbackend(UserIn in) {
        checkExistBlank(in.getUserName(), in.getPassword());
        if (in.isRegister()) {
            if (userMapper.selectCount(new User()) == 0) {
                User user = in.buildModel();
                user.setSessionId(NetUtil.getCurSessionId());
                user.setPassword(MessageDigestUtil.encoderByMd5(in.getPassword()));
                userMapper.insert(user);
                return user;
            }
            throw HttpStatusException.of(HttpStatus.METHOD_NOT_ALLOWED);
        }
        User record = new User();
        record.setUserName(in.getUserName());
        record.setPassword(MessageDigestUtil.encoderByMd5(in.getPassword()));
        User result = userMapper.selectOne(record);
        if (result != null) {
            // 回写sessionId
            result.setSessionId(webSessionSupport.logIn(result));
            User bean = new User();
            bean.setId(result.getId());
            bean.setSessionId(result.getSessionId());
            bean.setRemember(in.getRemember());
            userMapper.updateByPrimaryKeySelective(bean);
        } else {
            throw HttpStatusException.of(HttpStatus.UNAUTHORIZED);
        }
        return result;
    }

}
