package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByName(username);
    }

    /**
     * 用户登录查询
     *
     * @param username string
     * @return User
     */
    @Override
    public User userLogin(String username) {
        User user = userDao.getUserByName(username);
        return user;
    }

}
