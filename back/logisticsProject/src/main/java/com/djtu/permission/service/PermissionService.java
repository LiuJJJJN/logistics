package com.djtu.permission.service;

import com.djtu.permission.pojo.Role;

import java.util.List;

public interface PermissionService {

    /**
     * 获取角色对应的所有角色的所有功能
     * @param roleList 角色列表
     * @return 角色列表中所有角色的所有功能列表
     */
    List<Object> getPermissionListByRoleList(List<Role> roleList);

}
