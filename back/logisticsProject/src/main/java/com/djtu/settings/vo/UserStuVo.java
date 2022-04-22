package com.djtu.settings.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStuVo {
    String username;
    String password;
    String name;
    String sex;
    String college;
    String stuClass;
    String enterDate;
    String schoolSys;
}
