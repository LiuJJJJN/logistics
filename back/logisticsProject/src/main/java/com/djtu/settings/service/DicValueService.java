package com.djtu.settings.service;

import com.djtu.settings.pojo.DicValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DicValueService {

    List<DicValue> getCollegeList();
}
