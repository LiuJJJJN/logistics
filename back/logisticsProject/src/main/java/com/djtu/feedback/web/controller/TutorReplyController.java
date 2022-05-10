package com.djtu.feedback.web.controller;

import com.djtu.exception.ReplyException;
import com.djtu.response.Result;
import com.djtu.feedback.pojo.Feedback;
import com.djtu.feedback.pojo.Reply;
import com.djtu.settings.pojo.User;
import com.djtu.feedback.service.TutorReplyService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 导员对学生反馈的回复
 */
@Controller
@RequestMapping("/reply")
public class TutorReplyController {

    @Autowired
    TutorReplyService tutorReplyService;

    /**
     * 获取未回复的列表总数
     *
     * @param user id
     * @return 获取未反馈列表成功
     */
    @RequiresRoles("导员")
    @RequestMapping("/getUnNum.do")
    @ResponseBody
    public Result getUnNum(@RequestBody User user) {
        Map<String, Object> map = tutorReplyService.getUnNum(user);
        return new Result().setCode(200).setMessage("加载未反馈列表成功").setData(map);
    }

    /**
     * 导员未回复的学生反馈列表
     *
     * @param user 用户实例，夹带pageNo pageSize userid
     * @return 是否加载未答复的反馈学生信息
     * @throws ReplyException 加载未反馈列表失败
     */
    @RequiresRoles("导员")
    @RequestMapping("/getReplyL.do")
    @ResponseBody
    public Result getUnReplyList(@RequestBody User user) throws ReplyException {
        Map<String, Object> map = tutorReplyService.getUnReplyList(user);
        return new Result().setCode(200).setMessage("加载未反馈列表成功").setData(map);
    }

    /**
     * 导员-加载未反馈信息
     *
     * @param feedback 反馈信息实例
     * @return 是否加载反馈信息
     * @throws ReplyException 加载未反馈信息失败
     */
    @RequiresRoles("导员")
    @RequestMapping("/getFeedbackBI.do")
    @ResponseBody
    public Result getFeedbackInfById(@RequestBody Feedback feedback) throws ReplyException {
        String id = feedback.getId();
        Feedback f = tutorReplyService.getFeedbackInfById(id);
        return new Result().setCode(200).setMessage("加载未反馈信息成功").setData(f);
    }

    /**
     * 导员回复学生反馈
     *
     * @param reply 回复实例
     * @return 是否回复
     * @throws ReplyException 回复失败
     */
    @RequiresRoles("导员")
    @RequestMapping("/tutorReply.do")
    @ResponseBody
    public Result tutorReply(@RequestBody Reply reply) throws ReplyException {
        tutorReplyService.tutorReply(reply);
        return new Result().setCode(200).setMessage("回复成功");
    }

    /**
     * 获取历史反馈列表
     *
     * @param user 用户实例
     * @return 是否获取历史列表
     * @throws ReplyException 获取失败
     */
    @RequiresRoles("导员")
    @RequestMapping("/getRL.do")
    @ResponseBody
    public Result getReplyList(@RequestBody User user) throws ReplyException {
        Map<String, Object> map = tutorReplyService.getReplyList(user);
        return new Result().setCode(200).setMessage("获取成功").setData(map);
    }

    /**
     * 获取学生的反馈信息
     *
     * @param feedback 反馈信息
     * @return 获取学生的反馈信息成功
     * @throws ReplyException 获取学生的反馈信息失败
     */
    @RequiresRoles("导员")
    @RequestMapping("/getHistoryIBI.do")
    @ResponseBody
    public Result getHistoryInfById(@RequestBody Feedback feedback) throws ReplyException {
        Feedback feedback1 = tutorReplyService.getHistoryInfById(feedback);
        return new Result().setCode(200).setMessage("获取成功").setData(feedback1);
    }

    /**
     * 获取答复的信息
     *
     * @param feedback 反馈信息
     * @return 获取答复信息成功
     * @throws ReplyException 获取答复信息失败
     */
    @RequiresRoles("导员")
    @RequestMapping("/getReplyIBI.do")
    @ResponseBody
    public Result getReplyInfById(@RequestBody Feedback feedback) throws ReplyException {
        List<Reply> list = tutorReplyService.getReplyInfById(feedback);
        return new Result().setCode(200).setMessage("获取成功").setData(list);
    }

    /**
     * 再次回复学生反馈
     * @param reply 答复实例
     * @return 再次回复学生反馈成功
     * @throws ReplyException
     */
    @RequiresRoles("导员")
    @RequestMapping("/replyAgain.do")
    @ResponseBody
    public Result replyAgain(@RequestBody Reply reply) throws ReplyException{
        tutorReplyService.replyAgain(reply);
        return new Result().setCode(200).setMessage("再次回复成功");
    }
}
