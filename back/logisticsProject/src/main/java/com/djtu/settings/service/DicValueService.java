package com.djtu.settings.service;

import com.djtu.exception.DictionaryException;
import com.djtu.settings.pojo.DicValue;
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
     * @param value 更改后的值
     * @param id 主键
     */
    void updateDicValues(String value, String id) throws DictionaryException;
}
