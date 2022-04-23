package com.djtu.settings.dao;


import com.djtu.settings.pojo.Role;

import java.util.List;

public interface RoleDao {

    /**
     * 根据用户 id 查找对应的角色列表
     * @param userId 用户 id
     * @return 角色列表
     */
    List<Role> getRoleListByUserId(String userId);

}
