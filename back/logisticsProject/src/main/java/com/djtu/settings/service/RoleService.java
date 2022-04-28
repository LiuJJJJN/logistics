package com.djtu.settings.service;

import com.djtu.settings.pojo.Role;
import com.djtu.utils.StringUtil;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 通过 user 表的 user_id 查询对应的角色
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> getRoleListByUserId(String userId);

    /**
     * 向用户角色中间表中添加内容
     * @param userId 用户id
     * @param integer 角色id
     * @param id 主键
     * @return 添加成功的条数
     */
    int addUserRole(String userId, Integer integer, String id);

    /**
     * 删除用户角色中间表中内容
     * @param userId 用户id
     * @param integer 角色id
     * @return 操作影响条数
     */
    int removeUserRole(String userId, Integer integer);

    /**
     * 通过userId查询关联的所有角色集合
     * @param userId 用户表id
     * @return 关联的角色集合
     */
    Set<String> getRolesByUserId(String userId);
}
