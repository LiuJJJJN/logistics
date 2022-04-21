package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;
import org.springframework.data.repository.query.Param;

public interface UserDao {
    //根据用户名查询
    User getUserByName(@Param("username") String username);
}
