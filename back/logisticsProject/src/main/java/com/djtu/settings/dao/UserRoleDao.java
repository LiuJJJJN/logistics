package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.UserRole;

public interface UserRoleDao {
    /**
     * 学生注册插入
     * @param userRole
     * @return
     */
    Integer setUserRole(UserRole userRole);
}
