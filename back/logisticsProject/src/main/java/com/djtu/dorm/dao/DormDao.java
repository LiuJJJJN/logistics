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
}
