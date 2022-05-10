package com.djtu.dorm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormApplyPageConditionVo {
    private String userId;
    private Integer pageNo;
    private Integer pageSize;

}
