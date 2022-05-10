package com.djtu.permission.service;

import com.djtu.permission.pojo.Role;
import com.djtu.permission.pojo.vo.StudentRoleVo;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.permission.pojo.vo.TutorRoleVo;
import com.djtu.settings.pojo.vo.TutorSearchVo;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 通过 user 表的 user_id 查询对应的角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> getRoleListByUserId(String userId);

    /**
     * 向用户角色中间表中添加内容
     *
     * @param userId  用户id
     * @param integer 角色id
     * @param id      主键
     * @return 添加成功的条数
     */
    int addUserRole(String userId, Integer integer, String id);

    /**
     * 删除用户角色中间表中内容
     *
     * @param userId  用户id
     * @param integer 角色id
     * @return 操作影响条数
     */
    int removeUserRole(String userId, Integer integer);

    /**
     * 通过userId查询关联的所有角色集合
     *
     * @param userId 用户表id
     * @return 关联的角色集合
     */
    Set<String> getRolesByUserId(String userId);

    /**
     * 分页查询学生用户角色相关信息
     *
     * @param pageCount 起始页
     * @param pageSize  数据量
     * @return 学生用户角色信息列表
     */
    List<StudentRoleVo> getStudentUserRoleVoList(StudentSearchVo studentSearchVo, int pageCount, int pageSize, String tutorId);

    /**
     * 分页查询导员用户角色相关信息
     *
     * @param tutorSearchVo 导员搜索信息
     * @param pageNo        起始页
     * @param pageSize      数据量
     * @return 数据导员及对应角色列表
     */
    List<TutorRoleVo> getTutorUserRoleVoList(TutorSearchVo tutorSearchVo, Integer pageNo, Integer pageSize);

    /**
     * 学生用户角色相关信息列表总数
     *
     * @return 学生用户角色信息列表数量
     */
    Integer getStudentRoleListTotal(StudentSearchVo studentSearchVo);

    /**
     * !!! 重写上方方法 !!!
     * 获取当前导员认领的学生总数
     *
     * @param tutorId         导员id
     * @param studentSearchVo 模糊查询
     * @return 符合条件的学生数量
     */
    Integer getStudentRoleListTotal(String tutorId, StudentSearchVo studentSearchVo);

    /**
     * 导员用户角色相关信息总数
     *
     * @param tutorSearchVo 导员查询条件
     * @return 总数量
     */
    Integer getTutorRoleListTotal(TutorSearchVo tutorSearchVo);
}
