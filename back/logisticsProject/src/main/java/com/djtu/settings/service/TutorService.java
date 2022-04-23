package com.djtu.settings.service;

import com.djtu.settings.vo.UserVo;

public interface TutorService {

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 导员用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByUsername(String username);
}
