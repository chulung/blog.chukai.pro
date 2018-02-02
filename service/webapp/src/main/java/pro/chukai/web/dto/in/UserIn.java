package pro.chukai.web.dto.in;

import pro.chukai.web.dto.BaseDto;
import pro.chukai.web.model.User;

/**
 * Created by chukai on 2017/7/9.
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
