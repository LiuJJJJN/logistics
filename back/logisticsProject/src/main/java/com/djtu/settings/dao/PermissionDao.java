package com.djtu.settings.dao;

import com.djtu.settings.pojo.Permission;
import com.djtu.settings.pojo.Role;

import java.util.List;
import java.util.Map;

public interface PermissionDao {

    /**
     * 添加权限方法
     * @param permission 权限
     */
    void addPermList(Permission permission);

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
