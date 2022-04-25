package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.*;

import java.util.List;

public interface UserService {

    /**
     * 学生注册
     * @param student 学生实例
     * @throws RegisterException 注册异常
     */
    void registerStudent(Student student) throws RegisterException;

    /**
     * 导员注册
     * @param tutor 导员
     * @throws RegisterException 注册异常
     */
    void registerTutor(Tutor tutor) throws RegisterException;

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 学生用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByStudentUsername(String username);

    /**
     * 分页查询学生用户角色相关信息
     * @param pageCount 起始页
     * @param pageSize 数据量
     * @return 学生用户角色信息列表
     */
    List<StudentRoleVo> getStudentUserRoleVoList(StudentSearchVo studentSearchVo, int pageCount, int pageSize);

    /**
     * 根据管理员 id 获取 user id
     * @param id 管理员 id
     * @return user id
     */
    String getUserIdByAdminId(String id);

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 管理员 用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByTutorUsername(String username);

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 导员用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByAdminUsername(String username);

    /**
     * 学生用户角色相关信息列表总数
     * @return 学生用户角色信息列表数量
     */
    Integer getStudentRoleListTotal(StudentSearchVo studentSearchVo);

    /**
     * 分页查询导员用户角色相关信息
     * @param tutorSearchVo 导员搜索信息
     * @param pageNo 起始页
     * @param pageSize 数据量
     * @return 数据导员及对应角色列表
     */
    List<TutorRoleVo> getTutorUserRoleVoList(TutorSearchVo tutorSearchVo, Integer pageNo, Integer pageSize);

    /**
     * 导员用户角色相关信息总数
     * @param tutorSearchVo 导员查询条件
     * @return 总数量
     */
    Integer getTutorRoleListTotal(TutorSearchVo tutorSearchVo);

}
