package com.djtu.settings.service;

import com.djtu.settings.pojo.vo.UserVo;

public interface TutorService {

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 导员用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByUsername(String username);

    /**
     * 根据导员 id 获取 user id
     * @param id 导员 id
     * @return user id
     */
    String getUserIdByTutorId(String id);

}
