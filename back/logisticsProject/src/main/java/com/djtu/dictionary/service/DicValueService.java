package com.djtu.dictionary.service;

import com.djtu.exception.DictionaryException;
import com.djtu.dictionary.pojo.DicValue;
import com.djtu.dictionary.pojo.vo.DicValueVo;
import com.djtu.exception.NothingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DicValueService {

    /**
     * 获取学院列表
     *
     * @return 学院列表
     */
    List<DicValue> getCollegeList();

    /**
     * 获取所有数据字典
     *
     * @return 数据字典列表
     */
    List<DicValue> getDicValuesList() throws NothingException;

    /**
     * 查询所有数据字典值，可根据value或type_code查询也可查询所有
     *
     * @param dicValueVo dicValueVo实例
     * @return 数据字典值列表
     */
    List<DicValue> getDicValuesByCodeOrValue(DicValueVo dicValueVo) throws NothingException;

    /**
     * 查询所有数据字典值个数
     *
     * @return 个数
     */
    Integer getDicValuesListNum();

    /**
     * 插入数据字典
     *
     * @param dicValue 数据字典值
     */
    void setDicValues(DicValue dicValue) throws DictionaryException;

    /**
     * 删除数据字典值数据
     *
     * @param data 数据字典值id集合
     */
    void delDicValues(List<String> data) throws DictionaryException;

    /**
     * 更新数据字典数据
     *
     * @param dicValue 数据字典值实例
     */
    void updateDicValues(DicValue dicValue) throws DictionaryException;

    /**
     * 获取楼宇类型列表
     *
     * @return 楼宇类型列表
     */
    List<DicValue> getBuildingTypeList() throws NothingException;
}
