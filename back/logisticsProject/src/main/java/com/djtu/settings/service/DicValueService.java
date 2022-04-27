package com.djtu.settings.service;

import com.djtu.exception.DictionaryException;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.vo.DicValueVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DicValueService {

    /**
     * 获取学院列表
     * @return 学院列表
     */
    List<DicValue> getCollegeList();

    /**
     * 获取所有数据字典
     * @return 数据字典列表
     */
    List<DicValue> getDicValuesList() throws DictionaryException;

    /**
     *查询所有数据字典值，可根据value或type_code查询也可查询所有
     * @param dicValueVo dicValueVo实例
     * @return 数据字典值列表
     */
    List<DicValue> getDicValuesByCodeOrValue(DicValueVo dicValueVo) throws DictionaryException;

    /**
     * 查询所有数据字典值个数
     * @return 个数
     */
    Integer getDicValuesListNum();

    /**
     * 插入数据字典
     * @param dicValue 数据字典
     */
    void setDicValues(DicValue dicValue) throws DictionaryException;

    /**
     * 删除数据字典数据
     * @param id 主键
     */
    void delDicValues(String id) throws DictionaryException;

    /**
     * 更新数据字典数据
     * @param dicValue 数据字典值实例
     */
    void updateDicValues(DicValue dicValue) throws DictionaryException;

}
