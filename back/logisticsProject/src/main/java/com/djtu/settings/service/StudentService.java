package com.djtu.settings.service;

import com.djtu.exception.RegisterException;

public interface StudentService {

    /**
     * 学生注册重名查询
     * @param username
     * @throws RegisterException
     */
    void registerStudentUserNameVerify(String username) throws RegisterException;

    /**
     * 获取学生总数
     * @return 学生总数
     */
    Integer getStudentTotal();
}
