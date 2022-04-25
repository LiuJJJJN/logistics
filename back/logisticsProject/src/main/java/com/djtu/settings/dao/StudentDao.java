package com.djtu.settings.dao;

import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.UserVo;

public interface StudentDao {

    /**
     * 根据用户名查询学生信息
     * @param username 学生用户名
     * @return 学生实例
     */
    Student getStudentByUsername(String username);

    /**
     * 根据学生的学号查询
     * @param sno
     * @return
     */
    Student getStudentBySno(String sno);

    /**
     * 注册学生
     * @param student 学生信息
     * @return 插入成功数量
     */
    Integer setStudent(Student student);

    /**
     * 获取学生总数
     * @return 学生数量
     */
    Integer getStudentTotal();
}
