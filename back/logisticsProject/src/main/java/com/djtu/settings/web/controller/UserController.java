package com.djtu.settings.web.controller;

import com.djtu.exception.RegisterException;
import com.djtu.exception.UserManagerException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.Admin;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.service.*;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import com.djtu.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录、注册、个人信息相关接口
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AdminService adminService;

    //文件绝对路径前置目录
    @Value("${IMAGE_ABSOLUTE_PATH}")
    private String ABSOLUTE_PATH;

    /**
     * 登录功能
     *
     * @param map 前端传来的用户信息、角色信息、是否七天免登录
     * @return 登录成功时返回token、时间戳、免登录标记、用户信息
     */
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Result<Map> login(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String ident = (String) map.get("ident");
        Boolean rememberMe = (Boolean) map.get("rememberMe");

        //使用安全管理器创建 subject 对象
        Subject subject = SecurityUtils.getSubject();
        //创建 token                     用户名           公司     身份        过期时间 30分钟
        String jwt = JwtUtil.createJWT(username, "back", ident, 1000 * 60 * 30);
        //封装 token 为自定义 UsernamePasswordToken 类
        JwtToken jwtToken = new JwtToken(jwt, password);

        //执行 Shiro 的认证
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            return new Result().setCode(401).setMessage("账号不存在, 请重试");
        } catch (IncorrectCredentialsException e) {
            return new Result().setCode(401).setMessage("密码错误, 请重试");
        }

        //配置返回用户信息内容
        UserVo backUser = null;
        if ("学生".equals(ident)) {
            backUser = userService.getUserVoByStudentUsername(username);
        } else if ("导员".equals(ident)) {
            backUser = userService.getUserVoByTutorUsername(username);
        } else if ("管理员".equals(ident)) {
            backUser = userService.getUserVoByAdminUsername(username);
        }
        backUser.setPassword("");
        backUser.setSalt("");
        backUser.setPrimaryRole(ident);

        //创建返回数据集合
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("user", backUser); // 1.传递用户信息
        resultMap.put("token", jwt); // 2.传递 token
        String sessionId = (String) subject.getSession().getId();
        resultMap.put("sessionId", sessionId); // 3.传递 sessionId
        resultMap.put("rememberMe", rememberMe); // 4.传递是否七天免登录的标记
        long timeStamp = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7; //设置过期时间为 7 天
        if (!rememberMe) {
            timeStamp = System.currentTimeMillis() + 1000 * 60 * 30; //如果没选七天免登录的话, 过期时间为 30 分钟
        }
        resultMap.put("timestamp", timeStamp); // 5.传递过期时间戳

        //将此用户角色信息存至 session: 方便 Shiro 的授权方法使用
        subject.getSession().setAttribute("userVo", backUser);

        return new Result().setCode(200).setMessage("登录成功").setData(resultMap);
    }

    /**
     * 学生注册
     *
     * @param student 学生信息
     * @return 注册成功的返回信息
     * @throws RegisterException 注册异常
     */
    @RequestMapping("/registerStudent.do")
    @ResponseBody
    public Result registerStudent(@RequestBody Student student) throws RegisterException {
        //随机产生-盐
        String salt = StringUtil.rand4Str();
        student.setSalt(salt);
        //uuid
        student.setId(StringUtil.generateUUID());
        student.setEnterDate(student.getEnterDate().substring(0, 10));
        //密码通过盐与md5加密
        student.setPassword(StringUtil.md5(student.getPassword(), salt));
        //调用注册的业务方法
        userService.registerStudent(student);
        return new Result().setCode(200).setMessage("注册成功");
    }

    /**
     * 导员注册
     *
     * @param tutor 导员
     * @return 是否重名的返回信息
     * @throws RegisterException 注册异常
     */
    @RequestMapping("/registerTutor.do")
    @ResponseBody
    public Result registerTutor(@RequestBody Tutor tutor) throws RegisterException {
        //随机产生-盐
        String salt = StringUtil.rand4Str();
        tutor.setSalt(salt);
        //uuid
        tutor.setId(StringUtil.generateUUID());
        //密码通过盐与md5加密
        tutor.setPassword(StringUtil.md5(tutor.getPassword(), salt));
        //调用注册的业务方法
        userService.registerTutor(tutor);
        return new Result().setCode(200).setMessage("注册成功");
    }

    /**
     * 导员注册用户名查重验证
     *
     * @param username 导员用户名
     * @return 是否重名的返回信息
     * @throws RegisterException 注册异常
     */
    @RequestMapping("/registerTutorUV.do")
    @ResponseBody
    public Result registerTutorUserNameVerify(String username) throws RegisterException {
        tutorService.registerTutorUserNameVerify(username);
        return new Result().setCode(200).setMessage("用户名可用");
    }

    /**
     * 学生注册用户名查重验证
     *
     * @param username 学生用户名
     * @return 是否重名的返回信息
     * @throws RegisterException 注册异常
     */
    @RequestMapping("/registerStudentUV.do")
    @ResponseBody
    public Result registerStudentUserNameVerify(String username) throws RegisterException {
        studentService.registerStudentUserNameVerify(username);
        return new Result().setCode(200).setMessage("用户名可用");
    }

    /**
     * 获取当前登录用户个人信息
     *
     * @return 用户个人信息Vo
     */
    @RequiresRoles(value = {"学生", "导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getUserInfo.do")
    @ResponseBody
    public Result getUserInfoByUsernameIdent() {
        UserVo userVo = (UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo");
        if ("学生".equals(userVo.getPrimaryRole())) {
            Student student = studentService.getStudentByUsername(userVo.getUsername());
            student.setPassword(null);
            student.setSalt(null);
            if (student.getTutor() != null) {
                student.getTutor().setPassword(null);
                student.getTutor().setSalt(null);
            }
            return new Result().setCode(200).setMessage("学生信息获取成功").setData(student);
        }
        if ("导员".equals(userVo.getPrimaryRole())) {
            Tutor tutor = tutorService.getTutorByUsername(userVo.getUsername());
            tutor.setPassword(null);
            tutor.setSalt(null);
            return new Result().setCode(200).setMessage("导员信息获取成功").setData(tutor);
        }
        if ("管理员".equals(userVo.getPrimaryRole())) {
            Admin admin = adminService.getAdminByUsername(userVo.getUsername());
            admin.setPassword(null);
            admin.setSalt(null);
            return new Result().setCode(200).setMessage("管理员信息获取成功").setData(admin);
        }
        return new Result().setCode(401).setMessage("未查询到当前登录角色信息, 请重新登录");
    }

    /**
     * 修改学生的个人信息
     *
     * @param student 学生实例
     * @return 是否修改成功
     */
    @RequiresRoles("学生")
    @RequestMapping("/editStudentInfo.do")
    @ResponseBody
    public Result editUserInfo(@RequestBody Student student) {
        String salt = studentService.getStudentSaltById(student.getId());
        student.setPassword(StringUtil.md5(student.getPassword(), salt));
        int res = studentService.editStudent(student);
        if (res == 1) {
            return new Result().setCode(200).setMessage("修改学生信息成功, 请重新登录");
        }
        return new Result().setCode(402).setMessage("修改学生信息失败, 请尝试修改用户名或学号");
    }

    /**
     * 修改导员的个人信息
     *
     * @param tutor 导员实例
     * @return 是否修改成功
     */
    @RequiresRoles("导员")
    @RequestMapping("/editTutorInfo.do")
    @ResponseBody
    public Result editTutorInfo(@RequestBody Tutor tutor) {
        String salt = tutorService.getTutorSaltById(tutor.getId());
        tutor.setPassword(StringUtil.md5(tutor.getPassword(), salt));
        int res = tutorService.editTutor(tutor);
        if (res == 1) {
            return new Result().setCode(200).setMessage("修改导员信息成功, 请重新登录");
        }
        return new Result().setCode(402).setMessage("修改导员信息失败, 请尝试修改用户名");
    }

    /**
     * 修改管理员的个人信息
     *
     * @param admin 管理员实例
     * @return 是否修改成功
     */
    @RequiresRoles("管理员")
    @RequestMapping("/editAdminInfo.do")
    @ResponseBody
    public Result editAdminInfo(@RequestBody Admin admin) {
        String salt = adminService.getAdminSaltById(admin.getId());
        admin.setPassword(StringUtil.md5(admin.getPassword(), salt));
        int res = adminService.editAdmin(admin);
        if (res == 1) {
            return new Result().setCode(200).setMessage("修改管理员信息成功, 请重新登录");
        }
        return new Result().setCode(402).setMessage("修改管理员信息失败, 请尝试修改用户名");
    }

    /**
     * 头像上传接口
     *
     * @param file 头像文件
     * @return 是否上传成功
     * @throws UserManagerException 数据库头像路径更新错误
     */
    @RequiresRoles(value = {"学生", "导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/uploadAvatar.do")
    @ResponseBody
    public String uploadAvatar(MultipartFile file) throws UserManagerException {
        UserVo userVo = (UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo");
        String userId = userVo.getUserId();
        String avatarPath = null;
        if ("学生".equals(userVo.getPrimaryRole())) {
            String studentId = userService.getStudentIdByUserId(userId);
            avatarPath = '/' + studentId + '/' + file.getOriginalFilename();
            studentService.setAvatarPath(userVo.getUsername(), avatarPath);
            File diskPath = new File(ABSOLUTE_PATH + studentId);
            diskPath.mkdirs();
        } else if ("导员".equals(userVo.getPrimaryRole())) {
            String tutorId = userService.getTutorIdByUserId(userId);
            avatarPath = '/' + tutorId + '/' + file.getOriginalFilename();
            tutorService.setAvatarPath(userVo.getUsername(), avatarPath);
            File diskPath = new File(ABSOLUTE_PATH + tutorId);
            diskPath.mkdirs();
        }
        try {
            file.transferTo(new File(ABSOLUTE_PATH + avatarPath));
        } catch (IOException e) {
            return null;
        }

        return null;
    }

}
