package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.userEntity.Profile;
import by.megumin.entity.userEntity.User;

public interface ProfileDao extends BaseDao<Profile> {
    Profile getByUser(User user);
}
