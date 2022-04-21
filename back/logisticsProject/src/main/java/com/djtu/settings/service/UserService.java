package com.djtu.settings.service;

import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.Users;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
//    List<Users> getAllUsers();

    User userLogin(String username);

}
