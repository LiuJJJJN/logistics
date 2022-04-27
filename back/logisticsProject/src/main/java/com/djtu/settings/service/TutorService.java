package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.UserVo;

public interface TutorService {

    /**
     * 教职工注册重名查询
     * @param username
     * @throws RegisterException
     */
    void registerTutorUserNameVerify(String username) throws RegisterException;

    /**
     * 根据导员username获取导员实例
     * @param username username
     * @return 导员实例
     */
    Tutor getTutorByUsername(String username);
}
