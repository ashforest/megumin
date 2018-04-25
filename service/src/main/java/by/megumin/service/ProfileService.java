package by.megumin.service;

import by.megumin.entity.userEntity.Profile;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseService;

public interface ProfileService extends BaseService<Profile> {
    Profile getByUser(User user);
}
