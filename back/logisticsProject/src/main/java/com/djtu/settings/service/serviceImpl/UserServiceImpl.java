package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUsername(String username) {
        return new User("001","mike", "123456", "");
    }

}
