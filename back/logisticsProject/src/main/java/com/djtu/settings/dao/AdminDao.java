package com.djtu.settings.dao;

import com.djtu.settings.pojo.Admin;

public interface AdminDao {

    /**
     * 根据管理员用户名查询管理员
     * @param username 管理员用户名
     * @return 管理员实例
     */
    Admin getAdminByUsername(String username);
}
