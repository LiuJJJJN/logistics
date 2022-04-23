package com.djtu.settings.service;

import com.djtu.settings.dao.PermissionDao;
import com.djtu.settings.pojo.Permission;
import com.djtu.settings.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PermissionService {

    /**
     * 获取角色对应的所有角色的所有功能
     * @param roleList 角色列表
     * @return 角色列表中所有角色的所有功能列表
     */
    List<Object> getPermissionListByRoleList(List<Role> roleList);

}
