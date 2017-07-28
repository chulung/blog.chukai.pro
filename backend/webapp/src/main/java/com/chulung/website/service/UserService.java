package com.chulung.website.service;

import com.chulung.website.dto.in.UserIn;
import com.chulung.website.model.User;

public interface UserService {
    User logInbackend(UserIn user);
}
