package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.userEntity.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    User getByLogin(String login);
}
