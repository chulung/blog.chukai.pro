package com.wchukai.web.dto.in;

import com.wchukai.web.dto.BaseDto;
import com.wchukai.web.model.User;

/**
 * Created by wchukai on 2017/7/9.
 */
public class UserIn extends User implements BaseDto<UserIn, User> {
    private boolean register;

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }
}
