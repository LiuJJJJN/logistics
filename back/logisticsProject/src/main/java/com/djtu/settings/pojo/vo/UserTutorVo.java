package com.djtu.settings.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教职工vo类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTutorVo {
    String userId;
    String username;
    String password;
    String salt;
    String name;
    String sex;
    String ident = "1";
    String studentId;
    String schoolSys;
    String enterDate;
    String college;
}
