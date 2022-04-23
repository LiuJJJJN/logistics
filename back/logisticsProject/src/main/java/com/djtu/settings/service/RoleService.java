package com.djtu.settings.service;

import com.djtu.settings.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 通过 user 表的 user_id 查询对应的角色
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> getRoleListByUserId(String userId);

}
