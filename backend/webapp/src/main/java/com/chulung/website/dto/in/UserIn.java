package com.chulung.website.dto.in;

import com.chulung.website.dto.BaseDto;
import com.chulung.website.model.User;

/**
 * Created by chulung on 2017/7/9.
 */
public class UserIn extends User implements BaseDto<UserIn,User> {
    private boolean register;

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }
}
