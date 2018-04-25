package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.userEntity.Profile;
import by.megumin.entity.userEntity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {
    @Override
    public Profile getByUser(User user) {
        List <Profile> profiles = getSessionFactory().getCurrentSession()
                .createQuery("select p from Profile p where p.user.id=:id", Profile.class)
                .setParameter("id", user.getId())
                .getResultList();
        return profiles.size() > 0 ? profiles.get(0) : null;
    }
}