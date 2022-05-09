package com.djtu.dorm.dao;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.settings.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DormDao {

    /**
     * 根据DormVo添加寝室
     *
     * @param dormVo 寝室vo
     * @return 影响条数
     */
    int addDormByDormVo(DormVo dormVo);

    /**
     * 获取寝室列表
     *
     * @return 寝室列表
     */
    List<Dorm> getDormList();

    /**
     * 根据寝室id删除寝室记录
     *
     * @param id 寝室id
     * @return 影响条数
     */
    int deleteDormByDormId(String id);

    /**
     * 修改寝室信息根据DormVo中的id
     *
     * @param dormVo dormVo
     * @return 影响条数
     */
    int editDormByDormVo(DormVo dormVo);

    /**
     * 根据楼宇id获取寝室列表
     *
     * @param id 楼宇id
     * @return 寝室列表
     */
    List<Dorm> getDormListByBuildingId(String id);

    /**
     * 根据寝室id查询寝室size大小
     *
     * @param dormId 寝室id
     * @return 寝室size
     */
    int getDormSizeByDormId(String dormId);

    /**
     * 根据用户id获取寝室信息
     *
     * @param userId 用户id
     * @return 寝室实例
     */
    Dorm getDormByUserId(String userId);

    /**
     * 根据用户id获取室友列表
     *
     * @param userId 用户id
     * @return 室友列表
     */
    List<Student> getDormFriendByUserId(String userId);

    /**
     * 根据用户id获取对应的寝室id
     *
     * @return 寝室id
     */
    String getDormIdByUserId(String userId);

    /**
     * 查询当前用户的正在申请订单数量
     *
     * @param stuId 学生id
     * @return 数量
     */
    int getApplyCountByStudentId(String stuId);

    /**
     * 添加换寝申请
     *
     * @param generateUUID id
     * @param stuId        学生id
     * @param fromDorm     现寝室
     * @param toDorm       意向寝室
     * @param status       状态
     * @return 影响条数
     */
    int addChangeDormApply(@Param("id") String generateUUID,
                           @Param("stuId") String stuId,
                           @Param("fromDorm") String fromDorm,
                           @Param("toDorm") String toDorm,
                           @Param("status") int status);

    /**
     * 根据当前学生id获取换寝状态
     *
     * @param stuId 学生id
     * @return 状态
     */
    Integer getStatusByStuId(String stuId);
}
