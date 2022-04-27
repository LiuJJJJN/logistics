package com.djtu.settings.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在用户登录后, 返回封装的统一类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String userId;
    private String PrimaryRole;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String sex;
    private String remark;
    private String avatarPath;

    public UserVo(String userId, String username, String password, String salt, String name, String sex, String remark, String avatarPath) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.sex = sex;
        this.remark = remark;
        this.avatarPath = avatarPath;
    }

}
