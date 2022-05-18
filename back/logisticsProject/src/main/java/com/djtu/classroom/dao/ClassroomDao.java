package com.djtu.classroom.dao;


import com.djtu.classroom.pojo.Classroom;
import com.djtu.classroom.pojo.vo.BuildingClassroomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassroomDao {

    /**
     *获取教室和教室对应的楼宇信息，可分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<BuildingClassroomVo> getClassroomAllList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 获取总条数
     * @return
     */
    Integer getClassroomAllTotal();

    /**
     * 根据id修改教室信息
     * @param classroom 教室实例
     * @return
     */
    Integer editClassroomInfById(Classroom classroom);

    /**
     * 根据id删除教室信息
     * @param id
     * @return
     */
    Integer deleteClassroomById(String id);

    /**
     * 添加教室信息
     * @param classroom
     * @return
     */
    Integer addClassroomInf(Classroom classroom);
}
