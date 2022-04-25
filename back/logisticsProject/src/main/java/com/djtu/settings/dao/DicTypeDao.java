package com.djtu.settings.dao;


import com.djtu.settings.pojo.DicType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicTypeDao {

    /**
     * 获取所有数据字典类型
     * @return
     */
    List<DicType> getDicTypeList();

    /**
     * 插入数据字典类型
     * @param dicType
     * @return
     */
    Integer setDicType(DicType dicType);

    /**
     *删除数据字典类型
     * @param code
     */
    Integer delDicType(String code);

    /**
     * 修改数据字典类型
     * @param dicType  数据字典实例
     * @return 影响行数
     */
    Integer updateDicType(DicType dicType);

    /**
     * 数据字典类型-根据id获取code
     * @param id 主键
     * @return 返回code
     */
    String getCodeById(String id);
}
