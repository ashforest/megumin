package by.megumin.service;

import by.megumin.dao.ProfileDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.userEntity.Profile;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl extends BaseServiceImpl<Profile> implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Override
    public Profile getByUser(User user) {
        return profileDao.getByUser(user);
    }

    @Override
    protected BaseDao<Profile> getDao() {
        return profileDao;
    }
}
