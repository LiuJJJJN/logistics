package com.djtu.settings.dao;

import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.vo.DicValueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicValueDao {
    /**
     * 根据code获取学院信息
     * @return 字典值列表
     */
    List<DicValue> getCollegeList();

    /**
     * 查询所有数据字典值，可根据value或type_code查询也可查询所有
     * @param dicValueVo dicValueVo实例
     * @return  数据字典值列表
     */
    List<DicValue> getDicValuesByCodeOrName(DicValueVo dicValueVo);

    /**
     * 查询所有数据字典值个数
     * @return 个数
     */
    Integer getDicValuesListNum();

    /**
     * 插入数据字典
     * @param dicValue
     * @return
     */
    Integer insertDicValue(DicValue dicValue);

    /**
     * 根据id删除数据字典
     * @param data
     * @return
     */
    Integer deleteDicValue(List<String> data);

    /**
     * 更新数据字典的值
     * @param dicValue 数据字典值实例
     * @return
     */
    Integer updateDicValue(DicValue dicValue);

    /**
     * 查询所有的数据字典
     * @return
     */
    List<DicValue> getDicValueList();

    /**
     * 根据type_code=code的个数
     * @param code code值
     * @return 个数
     */
    Integer getDicValueByCode(String code);

    /**
     * 更新DicValue表里的code
     * @param oldCode
     * @param newCode
     * @return
     */
    Integer updateDicValueOfCode(@Param("oldCode")String oldCode, @Param("newCode")String newCode);

    /**
     * 获取所有学院列表
     * @return
     */
    List<DicValue> getAllCollege();

}
