package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导员详细信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    private String id;        //uuid
    private String address;   //住址
    private String college;   //所属学院
    private String dorm_id;   //值班入住的寝室id
    private String remark;    //备注信息

}
