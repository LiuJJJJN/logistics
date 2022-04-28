package com.djtu.settings.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生搜索vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSearchVo implements Serializable {
    String name;
    String sno;
    String college;
    String stuClass;
    String startDate;
    String endDate;

}
