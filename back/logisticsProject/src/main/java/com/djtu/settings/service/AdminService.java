package com.djtu.settings.service;

import com.djtu.settings.pojo.vo.UserVo;

public interface AdminService {

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 管理员 用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByUsername(String username);

    /**
     * 根据管理员 id 获取 user id
     * @param id 管理员 id
     * @return user id
     */
    String getUserIdByAdminId(String id);
}
