package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.UserRoleVo;

import java.util.List;

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

    /**
     * 分页查询学生用户角色相关信息
     * @param pageCount 起始页
     * @param pageSize 数据量
     * @return 学生用户角色信息列表
     */
    List<UserRoleVo> getStudentUserRoleVoList(int pageCount, int pageSize);

    /**
     * 教职工注册重名查询
     * @param username
     * @throws RegisterException
     */
    void registerTutorUserNameVerify(String username) throws RegisterException;

    /**
     * 学生注册重名查询
     * @param username
     * @throws RegisterException
     */
    void registerStudentUserNameVerify(String username) throws RegisterException;
}
