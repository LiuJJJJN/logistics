package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.User;

public interface UserService {

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户实例
     */
    User getUserByUsername(String username);

    /**
     * 学生注册
     * @param
     */
    void registerStudent(Student student) throws RegisterException;

    /**
     * 教职工注册
     * @param tutor
     * @throws RegisterException
     */
    void registerTutor(Tutor tutor) throws RegisterException;
}
