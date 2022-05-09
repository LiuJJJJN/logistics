package com.djtu.dorm.service;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.exception.DormException;
import com.djtu.exception.NothingException;
import com.djtu.settings.pojo.Student;

import java.util.List;

public interface DormService {

    /**
     * 根据DormVo中的内容添加寝室
     *
     * @param dormVo dormVo
     */
    void addDormByDormVo(DormVo dormVo) throws DormException;

    /**
     * 获取全部寝室信息列表
     *
     * @return 寝室列表
     */
    List<Dorm> getDormList() throws DormException;

    /**
     * 根据寝室id删除寝室
     *
     * @param id 寝室id
     */
    void deleteDormByDormId(String id) throws DormException;

    /**
     * 根据DormVo中内容修改寝室信息
     *
     * @param dormVo dormVo
     */
    void editDormByDormVo(DormVo dormVo) throws DormException;

    /**
     * 获取楼宇和寝室级联列表
     *
     * @return 列表
     */
    List<Object> getBuildingDormOptions();

    /**
     * 根据用户id查询寝室和楼宇相关信息
     *
     * @param userId 用户id
     * @return 寝室实例
     */
    Dorm getDormByUserId(String userId) throws NothingException;

    /**
     * 根据用户id查询室友列表
     *
     * @param userId 用户id
     * @return 室友列表
     */
    List<Student> getDormFriendByUserId(String userId) throws NothingException;

    /**
     * 更换寝室申请
     *
     * @param fromDorm 现寝室
     * @param toDorm 意向寝室
     */
    void addChangeDormApply(String stuId, String fromDorm, String toDorm) throws DormException;

    /**
     * 根据学生id获取当前换寝状态
     *
     * @param stuId 学生id
     * @return 当前状态
     */
    Integer getStatusByStuId(String stuId) throws NothingException;
}
