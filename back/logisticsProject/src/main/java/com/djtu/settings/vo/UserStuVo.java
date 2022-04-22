package com.djtu.settings.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStuVo {
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
    String stuClass;
    String sno;
}
