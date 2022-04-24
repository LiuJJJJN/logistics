package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;

public interface UserService {

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
