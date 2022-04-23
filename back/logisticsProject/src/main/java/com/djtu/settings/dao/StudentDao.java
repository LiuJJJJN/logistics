package com.djtu.settings.dao;

import com.djtu.settings.pojo.Student;
import com.djtu.settings.vo.UserVo;

public interface StudentDao {

    /**
     * 根据用户名查询学生信息
     * @param username 学生用户名
     * @return 学生实例
     */
    Student getStudentByUsername(String username);
}
