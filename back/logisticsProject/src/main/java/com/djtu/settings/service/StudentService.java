package com.djtu.settings.service;


import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.UserVo;

import java.util.List;

public interface StudentService {

    /**
     * 根据用户名获取用户公共字段用于返回前端
     * @param username 学生用户名
     * @return 用户 vo 类
     */
    UserVo getUserVoByUsername(String username);

    /**
     * 根据学生 id 获取 user id
     * @param id 学生 id
     * @return 学生 id
     */
    String getUserIdByStudentId(String id);


}
