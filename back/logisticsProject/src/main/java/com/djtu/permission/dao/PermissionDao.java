package com.djtu.permission.dao;

import com.djtu.permission.pojo.Permission;

import java.util.List;

public interface PermissionDao {

    /**
     * 根据父权限查找对应的子权限
     * @param fatherId 父权限 id
     * @return 对应的子权限列表
     */
    List<Permission> getSubPermissionListByFatherId(String fatherId);

    /**
     * 根据角色查找对应的父权限列表
     * @param id 角色 id
     * @return 该角色的父权限列表
     */
    List<Permission> getFatherPermissionListByRoleId(String id);

}
