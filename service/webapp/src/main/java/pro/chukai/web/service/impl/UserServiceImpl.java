package pro.chukai.web.service.impl;

import pro.chukai.common.util.MessageDigestUtil;
import pro.chukai.common.util.NetUtil;
import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.exception.HttpStatusException;
import pro.chukai.web.mapper.UserMapper;
import pro.chukai.web.model.User;
import pro.chukai.web.service.UserService;
import pro.chukai.web.session.WebSessionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.exception.HttpStatusException;
import pro.chukai.web.mapper.UserMapper;
import pro.chukai.web.model.User;
import pro.chukai.web.service.UserService;
import pro.chukai.web.session.WebSessionSupport;

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
