package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUsername(String username) {
        if ("mike".equals(username)) {
            return new User("001","mike", "fc1709d0a95a6be30bc5926fdb7f22f4", "");
        }
        return null;
    }

}