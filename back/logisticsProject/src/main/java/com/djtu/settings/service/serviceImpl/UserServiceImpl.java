package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.UsersDao;
import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.Users;
import com.djtu.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "usersDao")
    private UsersDao usersDao;

    @Override
    public User getUserByUsername(String username) {
        if ("mike".equals(username)) {
            return new User("001","mike", "fc1709d0a95a6be30bc5926fdb7f22f4", "");
        }
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> list=usersDao.getUsers();
        return list;
    }
}
