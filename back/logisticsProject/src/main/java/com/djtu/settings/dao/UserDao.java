package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;

public interface UserDao {

    String getUserIdByStudentId(String id);

    String getUserIdByTutorId(String id);

    String getUserIdByAdminId(String id);

    /**
     * 学生注册向用户表插入数据
     */
    Integer setUser(User user);
}
