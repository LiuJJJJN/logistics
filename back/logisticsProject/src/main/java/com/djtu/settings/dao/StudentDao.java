package com.djtu.settings.dao;

import com.djtu.settings.pojo.Student;
import com.djtu.settings.vo.UserVo;

public interface StudentDao {
    Student getStudentByUsername(String username);
}
