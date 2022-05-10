package com.djtu.dorm.service;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormApplyPageConditionVo;
import com.djtu.dorm.pojo.vo.DormApplyVo;
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

    /**
     * 修改学生的换寝申请状态
     *
     * @param id 换寝申请表id
     * @param status 更改后的状态
     */
    void setDormStatusById(String id, String status) throws DormException;

    /**
     * 根据状态码获取换寝申请列表
     *
     * @return 申请列表
     */
    List<DormApplyVo> getDormChangeApplyList(DormApplyPageConditionVo tutorId) throws NothingException;

    /**
     * 完成换寝
     *
     * @param stuId 学生id
     */
    void finishChangeByStuId(String stuId) throws DormException;

    /**
     * 获取学生换寝申请记录总数
     *
     * @param vo tutorId
     * @return 数据总数
     */
    int getDormChangeApplyTotal(DormApplyPageConditionVo vo);
}
