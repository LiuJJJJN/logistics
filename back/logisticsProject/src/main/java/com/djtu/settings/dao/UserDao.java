package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.vo.StudentRoleVo;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 根据学生 id 查找对应的用户 id
     * @param id 学生 id
     * @return 对应的用户 id
     */
    String getUserIdByStudentId(String id);

    /**
     * 根据导员 id 查找对应的用户 id
     * @param id 导员 id
     * @return 对应的用户 id
     */
    String getUserIdByTutorId(String id);

    /**
     * 根据管理员 id 查找对应的用户 id
     * @param id 管理员 id
     * @return 对应的用户 id
     */
    String getUserIdByAdminId(String id);

    /**
     * 学生注册向用户表插入数据
     */
    Integer setStudentUser(User user);

    /**
     * 教职工注册向用户表插入数据
     * @param user
     * @return
     */
    Integer setTutorUser(User user);

    /**
     * 分页查询学生用户角色相关信息
     * @param pageCount 起始页
     * @param pageSize 数据量
     * @return 学生用户角色信息列表
     */
    List<StudentRoleVo> getStudentUserRoleVoList(@Param("vo") StudentSearchVo studentSearchVo, @Param("pageNo") int pageCount, @Param("pageSize") int pageSize);

    /**
     * 获取学生角色列表总数
     * @return 总数
     */
    Integer getStudentUserRoleVoListTotal(@Param("vo") StudentSearchVo vo);
}
