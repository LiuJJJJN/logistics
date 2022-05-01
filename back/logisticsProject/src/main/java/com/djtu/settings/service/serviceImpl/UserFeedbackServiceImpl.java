package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.FeedbackException;
import com.djtu.settings.dao.FeedbackDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Feedback;
import com.djtu.settings.service.UserFeedbackService;
import com.djtu.utils.DateTimeUtil;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

    @Autowired
    FeedbackDao feedbackDao;
    @Autowired
    UserDao userDao;
    private static final Integer SUCCESS_FLAG=1;

    @Override
    @Transactional(rollbackFor ={FeedbackException.class} )
    public void studentAddFeedback(Feedback feedback) throws FeedbackException{
        feedback.setId(StringUtil.generateUUID());
        feedback.setTime(DateTimeUtil.getSysTime());
        feedback.setStatus("0");
        //根据user_id得到stu_id
        String userId=feedback.getStuId();
        String stuId=userDao.getStudentIdByUserId(userId);
        //将stu_id放进实例中
        feedback.setStuId(stuId);
        Integer num=feedbackDao.studentAddFeedback(feedback);
        if(num<SUCCESS_FLAG){
            throw new FeedbackException("反馈失败");
        }
    }
}
