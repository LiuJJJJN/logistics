package com.djtu.permission.dao;


import com.djtu.permission.pojo.Role;
import com.djtu.permission.pojo.vo.StudentRoleVo;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.permission.pojo.vo.TutorRoleVo;
import com.djtu.settings.pojo.vo.TutorSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    /**
     * 根据用户 id 查找对应的角色列表
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    List<Role> getRoleListByUserId(String userId);

    /**
     * 添加用户和角色的关联关系
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @param id     id主键
     * @return 操作影响条数
     */
    int addUserRole(@Param("userId") String userId, @Param("roleId") Integer roleId, @Param("id") String id);

    /**
     * 删除用户角色中间表的字段
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 影响条数
     */
    int removeUserRole(@Param("userId") String userId, @Param("roleId") Integer roleId);

    /**
     * 根据用户id查询角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    Set<String> getRoleSetByUserId(String userId);

    /**
     * 分页查询学生用户角色相关信息
     *
     * @param pageCount 起始页
     * @param pageSize  数据量
     * @return 学生用户角色信息列表
     */
    List<StudentRoleVo> getStudentUserRoleVoList(@Param("vo") StudentSearchVo studentSearchVo,
                                                 @Param("pageNo") int pageCount,
                                                 @Param("pageSize") int pageSize);

    /**
     * 分页查询导员相关角色信息列表 + 模糊查询
     *
     * @param tutorSearchVo 导员模糊查询数据
     * @param pageNo        起始页
     * @param pageSize      数据量
     * @return 相关数据列表
     */
    List<TutorRoleVo> getTutorUserRoleVoList(@Param("vo") TutorSearchVo tutorSearchVo,
                                             @Param("pageNo") Integer pageNo,
                                             @Param("pageSize") Integer pageSize);

    /**
     * 获取学生角色列表总数
     *
     * @return 总数
     */
    Integer getStudentUserRoleVoListTotal(@Param("vo") StudentSearchVo vo);

    /**
     * 获取导员相关用户角色总数
     *
     * @param tutorSearchVo 模糊条件
     * @return 总数
     */
    Integer getTutorUserRoleVoListTotal(@Param("vo") TutorSearchVo tutorSearchVo);
}
