package pro.chukai.web.service;

import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.model.User;
import pro.chukai.web.dto.in.UserIn;

public interface UserService {
    User logInbackend(UserIn user);
}
