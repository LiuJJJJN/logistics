package com.djtu.settings.service;

import com.djtu.settings.pojo.Admin;

public interface AdminService {

    /**
     * 根据管理员username获取管理员实例
     * @param username username
     * @return 管理员实例
     */
    Admin getAdminByUsername(String username);

    /**
     * 根据管理员id获取管理员盐
     * @param id 管理员id
     * @return 盐
     */
    String getAdminSaltById(String id);

    /**
     * 修改管理员信息
     * @param admin 管理员实例
     * @return 修改影响条数
     */
    int editAdmin(Admin admin);
}
