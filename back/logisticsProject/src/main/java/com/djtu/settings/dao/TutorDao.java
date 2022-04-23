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

}
