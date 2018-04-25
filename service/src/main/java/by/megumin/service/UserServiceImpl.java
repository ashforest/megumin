package by.megumin.service;

import by.megumin.dao.UserDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }

    @Override
    public Long save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Long id = userDao.save(user);
        return id;
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find user.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                                                                        user.getPassword(),
                                                                        getUserAuthority(user));
    }

    private Set<GrantedAuthority> getUserAuthority(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
        return grantedAuthorities;
    }
}