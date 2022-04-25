package com.djtu.settings.service;

import com.djtu.exception.DictionaryException;
import com.djtu.settings.pojo.DicType;

import java.util.List;

public interface DicTypeService {

    /**
     * 查询所有数据字典类型
     * @return 数据字典类型列表
     */
    List<DicType> getDicTypeList() throws DictionaryException;

    /**
     * 插入数据字典类型
     * @param dicType 数据字典类型
     */
    void setDicType(DicType dicType) throws DictionaryException;

    /**
     * 删除数据字典类型
     * @param code
     */
    void delDicType(String code) throws DictionaryException;

    /**
     * 修改数据字典类型
     * @param dicType
     */
    void updateDicType(DicType dicType) throws DictionaryException;
}
