package com.djtu.settings.service;

import com.djtu.exception.UserManagerException;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.TutorVo;

import java.util.List;

public interface UserManageService {
    /**
     * 获取导员信息
     * @param tutorVo 导员实例
     * @return 导员信息列表
     */
    List<Tutor> getTutorList(TutorVo tutorVo) throws UserManagerException;

    /**
     * 导员记录总个数
     * @return 总个数
     */
    Integer gitTutorNum();

    /**
     * 学院列表
     * @return 学院列表
     */
    List<DicValue> getCollegeList();

    /**
     *重置导员密码6个0
     * @param data id列表
     */
    void resetTutorPwd(List<String> data) throws UserManagerException;

    /**
     * 批量删除导员
     * @param data id列表
     */
    void delTutorList(List<String> data) throws UserManagerException;
}
