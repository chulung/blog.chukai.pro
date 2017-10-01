package com.wchukai.web.service;

import com.wchukai.web.dto.in.UserIn;
import com.wchukai.web.model.User;

public interface UserService {
    User logInbackend(UserIn user);
}
