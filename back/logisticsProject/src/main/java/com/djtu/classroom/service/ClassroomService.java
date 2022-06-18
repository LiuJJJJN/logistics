package com.djtu.classroom.service;

import com.djtu.building.pojo.Building;
import com.djtu.building.pojo.vo.BuildingValueVo;
import com.djtu.exception.ClassroomException;

import java.util.List;
import java.util.Map;

public interface ClassroomService {

    /**
     * 获取教室信息列表
     * @param map 携带pageSize和pageNo
     * @return
     * @Exception
     */
    Map<String,Object> getClassroomList(Map map) throws ClassroomException;

    /**
     * 修改信息
     * @param map 修改信息map
     * @throws ClassroomException
     */
    void editClassroomInf(Map map) throws ClassroomException;

    /**
     * 删除教室信息
     * @param map 携带教室id的map
     * @throws ClassroomException
     */
    void deleteClassroom(Map map)throws ClassroomException;

    /**
     * 获取楼宇列表
     * @return
     */
    List<BuildingValueVo> getBuildingList() throws ClassroomException;

    /**
     * 添加教室信息
     * @param map 教室信息集合
     */
    void addClassroomInf(Map map) throws ClassroomException;
}
