package com.djtu.settings.dao;

import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.TutorVo;

import java.util.List;

public interface TutorDao {

    /**
     * 根据导员用户名查找导员信息
     * @param username 导员用户名
     * @return 导员实例
     */
    Tutor getTutorByUsername(String username);

    /**
     * 注册教职工
     * @param tutor
     * @return
     */
    Integer setTutor(Tutor tutor);

    /**
     * 获取导员信息列表
     * @param  tutorVo 导员实例
     * @return 导员信息集合
     */
    List<Tutor> getTutorList(TutorVo tutorVo);

    /**
     * 获取导员信息个数
     * @return 个数
     */
    Integer getTutorNum();

    /**
     * 根据uuid将导员密码初始化为0000
     * 同时修改了盐
     * @Param tutor 导员实例
     * @return 更改的个数
     */
    Integer resetPwdById(Tutor tutor);

    /**
     * 批量删除导员
     * @param list 导员列表
     * @return
     */
    Integer delTutorList(List<String> list);
}
