package com.djtu.dorm.dao;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormApplyPageConditionVo;
import com.djtu.dorm.pojo.vo.DormApplyVo;
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
     * @param time         时间
     * @return 影响条数
     */
    int addChangeDormApply(@Param("id") String generateUUID,
                           @Param("stuId") String stuId,
                           @Param("fromDorm") String fromDorm,
                           @Param("toDorm") String toDorm,
                           @Param("status") int status,
                           @Param("time") String time);

    /**
     * 根据当前学生id获取换寝状态
     *
     * @param stuId 学生id
     * @return 状态
     */
    Integer getStatusByStuId(String stuId);

    /**
     * 根据寝室号查询寝室id
     * @param doorNo
     * @return
     */
    String getIdByDoorNo(String doorNo);

    /**
     * 设置学生的换寝申请状态码
     *
     * @param id 换寝申请id
     * @param status 状态码
     * @return 影响条数
     */
    int setDormStatusById(@Param("id") String id, @Param("status") String status);

    /**
     * 完成换寝：将 status 设为 5
     *
     * @param stuId 学生id
     * @return 影响条数
     */
    int finishChangeByStuId(String stuId);

    /**
     * 根据状态码获取申请换寝的列表
     *
     * @return 申请换寝的列表
     */
    List<DormApplyVo> getDormChangeApplyList(@Param("vo") DormApplyPageConditionVo vo);

    /**
     * 获取学生换寝申请表记录总数
     *
     * @param vo tutorId
     * @return 记录总数
     */
    int getDormChangeApplyTotal(@Param("vo") DormApplyPageConditionVo vo);
}
