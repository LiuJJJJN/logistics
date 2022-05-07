package com.djtu.dorm.service;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.exception.DormException;

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
}
