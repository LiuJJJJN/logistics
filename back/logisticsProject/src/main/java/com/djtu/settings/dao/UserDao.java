package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 根据学生 id 查找对应的用户 id
     * @param id 学生 id
     * @return 对应的用户 id
     */
    String getUserIdByStudentId(String id);

    /**
     * 根据导员 id 查找对应的用户 id
     * @param id 导员 id
     * @return 对应的用户 id
     */
    String getUserIdByTutorId(String id);

    /**
     * 根据管理员 id 查找对应的用户 id
     * @param id 管理员 id
     * @return 对应的用户 id
     */
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

    /**
     * 根据tutorId获取user表里的id
     * @param data
     * @return
     */
    List<User> getIdByTutorId(List<String> data);

    /**
     * 根据totorId删除user表记录
     * @param data
     * @return
     */
    Integer delByTutorId(List<String> data);

    /**
     * 根据学生id列表获取对应的User列表
     * @param stuList 学生id列表
     * @return User列表
     */
    List<User> getUserIdListByStudentIdList(List<String> stuList);

    /**
     * 根据User id列表删除User对应字段
     * @param userList User id列表
     * @return 影响条数
     */
    int delUserByStudentId(List<User> userList);

    /**
     * 根据userId查询对应学生id
     * @param userId userId
     * @return 学生Id
     */
    String getStudentIdByUserId(String userId);

    /**
     * 根据userId查询对应导员id
     * @param userId userId
     * @return 导员Id
     */
    String getTutorIdByUserId(String userId);
}
