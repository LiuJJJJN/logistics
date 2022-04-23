package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;

public interface UserDao {

    String getUserIdByStudentId(String id);

    String getUserIdByTutorId(String id);

    String getUserIdByAdminId(String id);

    /**
     * 学生注册向用户表插入数据
     */
    Integer setStudentUser(User user);

    /**
     * 教职工注册向用户表插入数据
     * @param user
     * @return
     */
    Integer setTutorUser(User user);
}
