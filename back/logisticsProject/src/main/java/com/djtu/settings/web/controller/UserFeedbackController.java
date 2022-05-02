package com.djtu.settings.web.controller;

import com.djtu.exception.FeedbackException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.Feedback;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserFeedbackService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 学生反馈
 */
@Controller
@RequestMapping("/user")
public class UserFeedbackController {

    @Autowired
    private UserFeedbackService userFeedbackService;

    /**
     * 学生添加反馈
     * @param feedback 反馈实例
     * @return 是否反馈信息成功
     */
    @RequiresRoles("学生")
    @RequestMapping("/stuAddFb.do")
    @ResponseBody
    public Result studentAddFeedback(@RequestBody Feedback feedback) throws FeedbackException {
        //feedback里的id是userid
        userFeedbackService.studentAddFeedback(feedback);
        //UserVo userVo = (UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo");
        return new Result().setCode(200).setMessage("反馈成功");
    }

    /**
     * 获取反馈历史列表
     * @param user
     * @return 是否查询信息
     * @throws FeedbackException
     */
    @RequiresRoles("学生")
    @RequestMapping("/stuFdList.do")
    @ResponseBody
    public Result studentFeedbackList(@RequestBody User user) throws FeedbackException{
        List<Feedback> list= userFeedbackService.studentFeedbackList(user);
        Integer total=userFeedbackService.getListNum(user.getId());
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return new Result().setCode(200).setMessage("加载成功").setData(map);
    }

    /**
     *删除历史记录-实质是把标记改为1，原因是后期便于内部人员调取历史记录作为凭证
     * 并且导员那边也要有历史记录，如果删除，导员将看不到历史记录
     * @param data
     * @return
     * @throws FeedbackException
     */
    @RequiresRoles("学生")
    @RequestMapping("/delStuFL.do")
    @ResponseBody
    public Result delStudentFeedbackList(@RequestBody List<String> data)throws FeedbackException{
        userFeedbackService.delStudentFeedbackList(data);
        return new Result().setCode(200).setMessage("删除反馈历史记录成功");
    }

    /**
     * 加载反馈历史详细信息
     * @param feedback
     * @return 是否获取信息
     * @throws FeedbackException
     */
    //@RequiresRoles("学生")
    @RequestMapping("/getDI.do")
    @ResponseBody
    public Result getDetailedInf(@RequestBody Feedback feedback)throws FeedbackException{
        String id=feedback.getId();
        Map<String,Object> map=userFeedbackService.getDetailedInf(id);
        return new Result().setCode(200).setMessage("获取详细信息成功").setData(map);
    }

    @RequestMapping("/updD.do")
    @ResponseBody
    public Result updateDetail(@RequestBody Feedback feedback) throws FeedbackException{
        String f=feedback.getFeedback();
        String id=feedback.getId();
        userFeedbackService.updateDetail(f,id);
        return new Result().setCode(200).setMessage("修改反馈成功");
    }

}
