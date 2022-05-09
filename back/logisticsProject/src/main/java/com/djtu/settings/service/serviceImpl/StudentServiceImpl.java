package com.djtu.settings.service.serviceImpl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.MapUtils;

import com.djtu.dorm.dao.DormDao;
import com.djtu.exception.RegisterException;
import com.djtu.exception.UserManagerException;
import com.djtu.permission.pojo.vo.StudentDormVo;
import com.djtu.response.Result;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    UserDao userDao;
    private DormDao dormDao;

    @Override
    public void registerStudentUserNameVerify(String username) throws RegisterException {
        Student student = studentDao.getStudentByUsername(username);
        if (student != null) {
            throw new RegisterException("用户名已被注册");
        }
    }

    @Override
    public Integer getStudentTotal() {
        return studentDao.getStudentTotal();
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentDao.getStudentByUsername(username);
    }

    @Override
    public synchronized int editStudent(Student student) {
        Student oldStudent = studentDao.getStudentById(student.getId());

        int usernameCount = studentDao.getUsernameCountByUsername(student.getUsername());
        int snoCount = studentDao.getSnoCountBySno(student.getSno());

        if (oldStudent.getUsername().equals(student.getUsername()) || usernameCount == 0 &&
                oldStudent.getSno().equals(student.getSno()) || snoCount == 0) {

            return studentDao.editStudent(student);
        } else {
            return 0;
        }
    }

    @Override
    public String getStudentSaltById(String id) {
        return studentDao.getStudentSaltById(id);
    }

    @Override
    public void editStudentRemarkById(String id, String remark) throws UserManagerException {
        int res = studentDao.editStudentRemarkById(id, remark);
        if (res != 1) {
            throw new UserManagerException("学生备注修改失败");
        }
    }

    @Override
    public void setAvatarPath(String username, String avatarPath) throws UserManagerException {
        int res = studentDao.setStudentAvatarPathByUsername(username, avatarPath);
        if (res != 1) {
            throw new UserManagerException("学生头像设置失败");
        }
    }

    @Override
    public void downloadMyStudent(String id, HttpServletResponse response) throws IOException {
        //根据用户id获取导员id
        String tutorId=userDao.getTutorIdByUserId(id);
        //根据导员id获取学生
        List<StudentDormVo> list=studentDao.getStudentAndDormInfByTutorId(tutorId);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("学生", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //EasyExcel.write(response.getOutputStream()).sheet("学生表").doWrite(FileUtils.data());
        EasyExcel.write(response.getOutputStream(), StudentDormVo.class).sheet("模板").doWrite(list);
    }

    public synchronized void editStudentDormById(String id, String dormId) throws UserManagerException {
        int size = dormDao.getDormSizeByDormId(dormId);
        int count = studentDao.countDormByDormId(dormId);
        if (count == size) {
            throw new UserManagerException("寝室人满!");
        }
        int res = studentDao.editStudentDormById(id, dormId);
        if (res != 1) {
            throw new UserManagerException("修改学生寝室失败");
        }
    }

}
