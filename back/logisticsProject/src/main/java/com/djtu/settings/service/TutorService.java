package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.exception.UserManagerException;
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

    /**
     * 根据导员id获取盐
     * @param id 导员id
     * @return 盐
     */
    String getTutorSaltById(String id);

    /**
     * 修改导员个人信息
     * @param tutor 导员实例
     * @return 修改影响条数
     */
    int editTutor(Tutor tutor);

    /**
     * 为指定username的导员设置头像路径
     * @param username 用户名
     * @param avatarPath 头像路径
     */
    void setAvatarPath(String username, String avatarPath) throws UserManagerException;
}
