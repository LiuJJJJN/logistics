package com.djtu.settings.service;

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

}
