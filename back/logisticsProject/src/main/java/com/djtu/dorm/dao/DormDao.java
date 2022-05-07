package com.djtu.dorm.dao;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
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
}
