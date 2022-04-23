package com.djtu.settings.dao;

import com.djtu.settings.pojo.Tutor;

public interface TutorDao {
    /**
     * 根据教职工的用户名查询
     * @param username
     * @return
     */
    Tutor getTutorByUsername(String username);

    /**
     * 注册教职工
     * @param tutor
     * @return
     */
    Integer setTutor(Tutor tutor);

}
