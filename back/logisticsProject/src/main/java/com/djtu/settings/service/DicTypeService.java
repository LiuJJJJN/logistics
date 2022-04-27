package com.djtu.settings.service;

import com.djtu.exception.DictionaryException;
import com.djtu.settings.pojo.DicType;
import com.djtu.settings.pojo.vo.DicTypeVo;

import java.util.List;

public interface DicTypeService {

    /**
     * 查询所有数据字典类型的个数
     * @return 数据字典类型列表
     */
    Integer getDicTypeListNum() throws DictionaryException;

    /**
     * 查询所有数据字典类型
     * @return 数据字典类型列表
     */
    List<DicType> getDicTypeList() throws DictionaryException;

    /**
     * 根据code或name查询数据字典类型
     * @param dicTypeVo 数据字典vo实例
     * @return  数据字典列表
     */
    List<DicType> getDicTypeListByCodeOrName(DicTypeVo dicTypeVo);

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
