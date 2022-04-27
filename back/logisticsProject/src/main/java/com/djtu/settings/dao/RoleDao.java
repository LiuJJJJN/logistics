package com.djtu.settings.dao;


import com.djtu.settings.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    /**
     * 根据用户 id 查找对应的角色列表
     * @param userId 用户 id
     * @return 角色列表
     */
    List<Role> getRoleListByUserId(String userId);

    /**
     * 添加用户和角色的关联关系
     * @param userId 用户id
     * @param roleId 角色id
     * @param id id主键
     * @return 操作影响条数
     */
    int addUserRole(@Param("userId") String userId, @Param("roleId") Integer roleId, @Param("id")String id);

    /**
     * 删除用户角色中间表的字段
     * @param userId 用户id
     * @param roleId 角色id
     * @return 影响条数
     */
    int removeUserRole(@Param("userId") String userId, @Param("roleId") Integer roleId);

    /**
     * 根据用户id查询角色集合
     * @param userId 用户id
     * @return 角色集合
     */
    Set<String> getRoleSetByUserId(String userId);
}
