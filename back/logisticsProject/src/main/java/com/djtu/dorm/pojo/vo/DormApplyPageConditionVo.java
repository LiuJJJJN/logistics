package com.djtu.dorm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormApplyPageConditionVo implements Serializable {
    private String userId;
    private Integer pageNo;
    private Integer pageSize;

}
