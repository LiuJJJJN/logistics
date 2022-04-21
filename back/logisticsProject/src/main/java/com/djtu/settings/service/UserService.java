package com.djtu.settings.service;

import com.djtu.settings.pojo.User;

public interface UserService {
    User getUserByUsername(String username);
//    List<Users> getAllUsers();

    User userLogin(String username);

}
