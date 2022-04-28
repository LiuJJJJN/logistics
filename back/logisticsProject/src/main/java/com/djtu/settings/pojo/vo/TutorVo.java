package com.djtu.settings.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorVo {
    private String id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String sex;
    private String college;
    private String dormId;
    private String remark;
    private String avatarPath;
    private int pageNo;
    private int pageSize;
}
