package com.djtu.dorm.pojo.vo;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.settings.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormApplyVo {
    private String id;
    private Student student;
    private Dorm fromDorm;
    private Dorm toDorm;
    private Integer status;
    private String time;

}
