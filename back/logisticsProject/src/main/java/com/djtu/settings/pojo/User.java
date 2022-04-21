package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;          //uuid
    private String username;    //用户名
    private String password;    //密码(MD5)
    private String salt;        //盐
    private String name;        //名字
    private String sex;         //性别
    private String age;         //年龄
    private String tel;         //电话
    private String ident;       //身份
    private String ident_id;    //身份详细信息外键


}
