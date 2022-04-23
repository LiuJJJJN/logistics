package com.djtu.settings.service;

import com.djtu.settings.pojo.User;

public interface UserService {

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户实例
     */
    User getUserByUsername(String username);

}
