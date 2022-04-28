package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.settings.pojo.Student;

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

    /**
     * 根据学生username获取学生实例
     * @param username username
     * @return 学生实例
     */
    Student getStudentByUsername(String username);

    /**
     * 根据学生id修改学生信息
     * @param student 学生实例
     * @return 影响条数
     */
    int editStudent(Student student);

    /**
     * 修改密码时获取密码盐
     * @param id 学生id
     * @return 盐
     */
    String getStudentSaltById(String id);
}
