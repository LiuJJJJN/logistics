package com.djtu.settings.service;

import com.djtu.settings.pojo.User;
import com.djtu.settings.vo.UserStuVo;

public interface UserService {

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户实例
     */
    User getUserByUsername(String username);

    /**
     * 在 user 表和 student 表中插入学生数据
     * @param vo User 和 Student 表中的数据
     * @return 是否添加成功
     */
    boolean addUserStudent(UserStuVo vo);
}
