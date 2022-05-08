package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.exception.UserManagerException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.Student;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    /**
     * 根据学生id修改备注
     * @param id 学生id
     * @param remark 备注
     */
    void editStudentRemarkById(String id, String remark) throws UserManagerException;

    /**
     * 为指定username的学生设置头像路径
     * @param username 用户名
     * @param avatarPath 头像路径
     */
    void setAvatarPath(String username, String avatarPath) throws UserManagerException;

    /**
     * 下载我的学生Excel
     * @param id userId
     */
    void downloadMyStudent(String id, HttpServletResponse response) throws IOException;
}
