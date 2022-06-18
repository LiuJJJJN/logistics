package com.djtu.settings.service;

import com.djtu.exception.RegisterException;
import com.djtu.exception.UploadException;
import com.djtu.exception.UserManagerException;
import com.djtu.settings.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StudentService {

    /**
     * 学生注册重名查询
     * @param username
     * @throws RegisterException
     */
    void registerStudentUserNameVerify(String username) throws RegisterException;

    /**
     * 获取学生总数
     * @return 学生总数
     */
    Integer getStudentTotal();

    /**
     * 根据学生username获取学生实例
     * @param username username
     * @return 学生实例
     */
    Student getStudentByUsername(String username);

    /**
     * 根据学生id修改学生信息
     * @param student 学生实例
     * @return 影响条数
     */
    int editStudent(Student student);

    /**
     * 修改密码时获取密码盐
     * @param id 学生id
     * @return 盐
     */
    String getStudentSaltById(String id);

    /**
     * 根据学生id修改备注
     * @param id 学生id
     * @param remark 备注
     */
    void editStudentRemarkById(String id, String remark) throws UserManagerException;

    /**
     * 为指定username的学生设置头像路径
     * @param username 用户名
     * @param avatarPath 头像路径
     */
    void setAvatarPath(String username, String avatarPath) throws UserManagerException;

    /**
     * 下载我的学生Excel
     * @param id userId
     */
    void downloadMyStudent(String id, HttpServletResponse response) throws IOException;

    /**
     * 上传学生信息
     * @param userId 导员的用户id
     * @param file 文件
     * @param request 请求
     */
    void uploadMyStudent(String userId, MultipartFile file, HttpServletRequest request) throws IOException, UploadException;

    /**
     * 下载模板
     * @param response 响应
     */
    void downloadModel(HttpServletResponse response) throws IOException;

    /**
     * 管理员下载学生Excel
     * @param response 响应对象
     */
    void adminDownLoadStudent(HttpServletResponse response) throws IOException;

    /**
     * 管理员上传文件（学生表）
     * @param file 文件对象
     * @param request 请求对象
     * @throws IOException
     * @throws UploadException
     */
    void adminUpLoadStudent(MultipartFile file,HttpServletRequest request) throws IOException,UploadException;

    /**
     * 管理员-下载模板
     * @param response 响应对象
     */
    void adminDownLoadModel(HttpServletResponse response) throws IOException;

    /**
     * 修改学生寝室
     * @param id 学生id
     * @param dormId 寝室id
     */
    void editStudentDormById(String id, String dormId) throws UserManagerException;


}
