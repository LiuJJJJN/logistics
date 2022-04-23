package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.service.AdminService;
import com.djtu.settings.service.StudentService;
import com.djtu.settings.service.TutorService;
import com.djtu.settings.vo.UserVo;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
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
    private AdminService adminService;

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
        String jwt = JwtUtil.createJWT(username, "back", ident, 1000*60*30);
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
            backUser = studentService.getUserVoByUsername(username);
        }else if ("导员".equals(ident)) {
            backUser = tutorService.getUserVoByUsername(username);
        }else if ("管理员".equals(ident)) {
            backUser = adminService.getUserVoByUsername(username);
        }

        //配置返回 data 内容
        backUser.setPassword("");
        backUser.setSalt("");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("user", backUser); //传递用户信息
        resultMap.put("token", jwt); //传递token
        long timeStamp = System.currentTimeMillis()+1000*60*60*24*7; //设置过期时间为 7 天
        if (!rememberMe) {
            timeStamp = System.currentTimeMillis()+1000*60*30; //如果没选七天免登录的话, 过期时间为 30 分钟
        }
        resultMap.put("rememberMe", rememberMe); //传递是否七天免登录的标记
        resultMap.put("timestamp", timeStamp); //传递过期时间戳

        return new Result().setCode(200).setMessage("登录成功").setData(resultMap);
    }

}
