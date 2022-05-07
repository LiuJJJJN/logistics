package com.djtu.building.dao;

import com.djtu.building.pojo.Building;
import com.djtu.building.pojo.vo.BuildingValueVo;

import java.util.List;

public interface BuildingDao {

    /**
     * 添加楼宇信息
     *
     * @param building 楼宇实例
     * @return 影响条数
     */
    int addBuilding(Building building);

    /**
     * 获取所有楼宇列表
     *
     * @return 所有楼宇列表
     */
    List<Building> getBuildingList();

    /**
     * 修改了楼宇信息
     *
     * @param building 楼宇实例
     * @return 影响条数
     */
    int editBuilding(Building building);

    /**
     * 根据楼宇id删除楼宇
     *
     * @param id 楼宇id
     * @return 影响条数
     */
    int deleteBuildingById(String id);

    /**
     * 获取楼宇value列表
     *
     * @return 楼宇value列表
     */
    List<BuildingValueVo> getBuildingValueListByType(String type);
}
