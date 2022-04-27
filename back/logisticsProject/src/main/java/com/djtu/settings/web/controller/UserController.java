package com.djtu.settings.web.controller;

import com.djtu.exception.RegisterException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.DicValue;
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
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private UserService userService;

    /**
     * 登录功能
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

        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            return new Result().setCode(401).setMessage("账号不存在, 请等待管理员录入");
        } catch (IncorrectCredentialsException e) {
            return new Result().setCode(401).setMessage("密码错误, 请重试");
        }

        UserVo backUser = null;
        if ("学生".equals(ident)) {
            backUser = userService.getUserVoByStudentUsername(username);
        } else if ("导员".equals(ident)) {
            backUser = userService.getUserVoByTutorUsername(username);
        } else if ("管理员".equals(ident)) {
            backUser = userService.getUserVoByAdminUsername(username);
        }

        //配置返回 data 内容
        backUser.setPassword("");
        backUser.setSalt("");
        backUser.setPrimaryRole(ident);
        Map<String, Object> resultMap = new HashMap<>();
        String sessionId = (String) subject.getSession().getId();
        resultMap.put("sessionId", sessionId);
        resultMap.put("user", backUser); //传递用户信息
        resultMap.put("token", jwt); //传递token
        long timeStamp = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7; //设置过期时间为 7 天
        if (!rememberMe) {
            timeStamp = System.currentTimeMillis() + 1000 * 60 * 30; //如果没选七天免登录的话, 过期时间为 30 分钟
        }
        resultMap.put("rememberMe", rememberMe); //传递是否七天免登录的标记
        resultMap.put("timestamp", timeStamp); //传递过期时间戳

        return new Result().setCode(200).setMessage("登录成功").setData(resultMap);
    }

    /**
     * 学生注册
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

}
