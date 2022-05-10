package com.djtu.feedback.web.controller;

import com.djtu.exception.FeedbackException;
import com.djtu.exception.NothingException;
import com.djtu.response.Result;
import com.djtu.feedback.pojo.Feedback;
import com.djtu.settings.pojo.User;
import com.djtu.feedback.service.StudentFeedbackService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feedback")
public class StudentFeedbackController {

    @Autowired
    private StudentFeedbackService userFeedbackService;

    /**
     * 学生添加反馈
     *
     * @param feedback 反馈实例
     * @return 是否反馈信息成功
     */
    @RequiresRoles("学生")
    @RequestMapping("/stuAddFb.do")
    @ResponseBody
    public Result studentAddFeedback(@RequestBody Feedback feedback) throws FeedbackException {
        //feedback里的id是userid
        userFeedbackService.studentAddFeedback(feedback);
        return new Result().setCode(200).setMessage("反馈成功");
    }

    /**
     * 获取反馈历史列表
     *
     * @param user 这里实际上是分页数据
     * @return 是否查询信息
     * @throws FeedbackException 获取反馈历史记录失败
     */
    @RequiresRoles("学生")
    @RequestMapping("/stuFdList.do")
    @ResponseBody
    public Result studentFeedbackList(@RequestBody User user) throws NothingException, FeedbackException {
        List<Feedback> list = userFeedbackService.studentFeedbackList(user);
        Integer total = userFeedbackService.getListNum(user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return new Result().setCode(200).setMessage("加载成功").setData(map);
    }

    /**
     * 删除历史记录-实质是把标记改为1，原因是后期便于内部人员调取历史记录作为凭证
     * 并且导员那边也要有历史记录，如果删除，导员将看不到历史记录
     *
     * @param data 要删除的记录id
     * @return 删除成功提示
     * @throws FeedbackException 删除失败
     */
    @RequiresRoles("学生")
    @RequestMapping("/delStuFL.do")
    @ResponseBody
    public Result delStudentFeedbackList(@RequestBody List<String> data) throws FeedbackException {
        userFeedbackService.delStudentFeedbackList(data);
        return new Result().setCode(200).setMessage("删除反馈历史记录成功");
    }

    /**
     * 加载反馈历史详细信息
     *
     * @param feedback 反馈信息
     * @return 是否获取信息
     * @throws FeedbackException 获取反馈历史失败
     */
    @RequiresRoles("学生")
    @RequestMapping("/getDI.do")
    @ResponseBody
    public Result getDetailedInf(@RequestBody Feedback feedback) throws FeedbackException {
        String id = feedback.getId();
        Map<String, Object> map = userFeedbackService.getDetailedInf(id);
        return new Result().setCode(200).setMessage("获取详细信息成功").setData(map);
    }

    /**
     * 修改反馈接口 - 未完工.....
     *
     * @param feedback 反馈信息
     * @return 修改成功
     * @throws FeedbackException 修改失败异常
     */
    @RequestMapping("/updD.do")
    @ResponseBody
    public Result updateDetail(@RequestBody Feedback feedback) throws FeedbackException {
        String f = feedback.getFeedback();
        String id = feedback.getId();
        return new Result().setCode(200).setMessage("修改反馈成功");
    }

}
