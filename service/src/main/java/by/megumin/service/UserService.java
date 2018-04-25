package by.megumin.service;

import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService, BaseService<User> {
    User getByLogin(String  login);
}
