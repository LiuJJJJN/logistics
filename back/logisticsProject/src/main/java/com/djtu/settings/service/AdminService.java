package com.djtu.settings.service;

import com.djtu.settings.pojo.Admin;

public interface AdminService {

    /**
     * 根据管理员username获取管理员实例
     * @param username username
     * @return 管理员实例
     */
    Admin getAdminByUsername(String username);
}
