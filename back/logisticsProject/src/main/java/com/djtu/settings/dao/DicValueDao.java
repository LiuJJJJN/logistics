package com.djtu.settings.dao;

import com.djtu.settings.pojo.DicValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicValueDao {
    /**
     * 根据code获取学院信息
     * @return 字典值列表
     */
    List<DicValue> getCollegeList();

    /**
     * 插入数据字典
     * @param dicValue
     * @return
     */
    Integer insertDicValue(DicValue dicValue);

    /**
     * 根据id删除数据字典
     * @param id
     * @return
     */
    Integer deleteDicValue(String id);

    /**
     * 更新数据字典的值
     * @param value
     * @param id
     * @return
     */
    Integer updateDicValue(@Param("value")String value, @Param("id")String id);

    /**
     * 查询所有的数据字典
     * @return
     */
    List<DicValue> getDicValueList();
}
