package com.djtu.dictionary.dao;


import com.djtu.dictionary.pojo.DicType;
import com.djtu.dictionary.pojo.vo.DicTypeVo;

import java.util.List;

public interface DicTypeDao {

    /**
     * 获取所有数据字典类型
     * @return 数据字典类型列表
     */
    List<DicType> getDicTypeList();

    /**
     * 根据code或name获取数据字典
     * @param dicTypeVo 数据字典vo实例
     * @return 数据字典列表
     */
    List<DicType> getDicTypeListByCodeOrName(DicTypeVo dicTypeVo);

    /**
     * 插入数据字典类型
     * @param dicType 数据字典类型
     * @return 影响条数
     */
    Integer setDicType(DicType dicType);

    /**
     *删除数据字典类型
     * @param data id集合
     */
    Integer delDicType(List<String> data);

    /**
     * 修改数据字典类型
     * @param dicType 数据字典实例
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
