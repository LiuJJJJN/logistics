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

    /**
     * 根据学生id修改学生信息
     * @param student 学生实例
     * @return 影响条数
     */
    int editStudent(Student student);

    /**
     * 根据学生id查询学生信息
     * @param id 学生id
     * @return 学生实例
     */
    Student getStudentById(String id);

    /**
     * 查询库中用户名的数量
     * @param username 用户名
     * @return 有多少相同用户名
     */
    int getUsernameCountByUsername(String username);

    /**
     * 查询库中相同学号的数量
     * @param sno 学号
     * @return 有多少相同学号
     */
    int getSnoCountBySno(String sno);

    /**
     * 根据学生id获取盐
     * @param id 学生id
     * @return 盐
     */
    String getStudentSaltById(String id);
}
