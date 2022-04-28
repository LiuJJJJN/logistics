package com.djtu.settings.dao;

import com.djtu.settings.pojo.Tutor;

public interface TutorDao {

    /**
     * 根据导员用户名查找导员信息
     * @param username 导员用户名
     * @return 导员实例
     */
    Tutor getTutorByUsername(String username);

    /**
     * 注册教职工
     * @param tutor
     * @return
     */
    Integer setTutor(Tutor tutor);

    /**
     * 根据导员id获取盐
     * @param id 导员id
     * @return 盐
     */
    String getTutorSaltById(String id);

    /**
     * 根据导员id修改导员信息
     * @param tutor 导员实例
     * @return 影响条数
     */
    int editTutor(Tutor tutor);

    /**
     * 根据导员id查询导员信息
     * @param id 导员id
     * @return 导员实例
     */
    Tutor getTutorById(String id);

    /**
     * 查询导员表中有多少此用户名
     * @param username 用户名
     * @return 相同数量
     */
    int getUsernameCountByUsername(String username);
}
