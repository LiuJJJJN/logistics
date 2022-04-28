package com.djtu.settings.dao;

import com.djtu.settings.pojo.Admin;

public interface AdminDao {

    /**
     * 根据管理员用户名查询管理员
     * @param username 管理员用户名
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
     * 根据管理员id修改管理员信息
     * @param admin 管理员实例
     * @return 影响条数
     */
    int editAdmin(Admin admin);

    /**
     * 根据管理员id获取管理员实例
     * @param id 管理员id
     * @return 管理员实例
     */
    Admin getAdminById(String id);

    /**
     * 获取管理员表同名username数量
     * @param username 用户名
     * @return 同名数量
     */
    int getUsernameCountByUsername(String username);
}
