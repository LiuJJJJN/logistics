package com.djtu.settings.web.controller;

import com.djtu.exception.FeedbackException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.Feedback;
import com.djtu.settings.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/stuAddFb.do")
    @ResponseBody
    public Result studentAddFeedback(@RequestBody Feedback feedback) throws FeedbackException {
        //feedback里的id是userid
        userFeedbackService.studentAddFeedback(feedback);
        //UserVo userVo = (UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo");
        return new Result().setCode(200).setMessage("反馈成功");
    }
}
