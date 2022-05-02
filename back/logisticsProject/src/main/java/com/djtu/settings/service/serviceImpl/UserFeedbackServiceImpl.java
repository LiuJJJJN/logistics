package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.FeedbackException;
import com.djtu.settings.dao.FeedbackDao;
import com.djtu.settings.dao.ReplyDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Feedback;
import com.djtu.settings.pojo.Reply;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserFeedbackService;
import com.djtu.utils.DateTimeUtil;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

    @Autowired
    FeedbackDao feedbackDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ReplyDao replyDao;
    private static final Integer SUCCESS_FLAG=1;

    @Override
    @Transactional(rollbackFor ={FeedbackException.class} )
    public void studentAddFeedback(Feedback feedback) throws FeedbackException{
        feedback.setId(StringUtil.generateUUID());
        feedback.setTime(DateTimeUtil.getSysTime());
        feedback.setStatus("2");
        feedback.setDelFlag("0");//删除标记
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

    @Override
    public List<Feedback> studentFeedbackList(User user) throws FeedbackException{
        Integer pageNo=Integer.valueOf(user.getTutorId());
        Integer pageSize=Integer.valueOf(user.getAdminId());
        //获取该学生的stu_id
        String stuId=userDao.getStudentIdByUserId(user.getId());
        //根据学生id获取反馈列表
        List<Feedback> list=feedbackDao.getFeedbackByStudent(stuId,pageNo,pageSize);
        if(list.isEmpty()){
            throw new FeedbackException("未查询到反馈信息");
        }
        return list;
    }

    @Override
    public Integer getListNum(String id) throws FeedbackException{
        //获取该学生的stu_id
        String stuId=userDao.getStudentIdByUserId(id);
        //根据学生id获取列表记录数量
        Integer num=feedbackDao.getListNum(stuId);
        if(num<SUCCESS_FLAG){
            throw new FeedbackException("您没有反馈");
        }
        return num;
    }

    @Override
    public void delStudentFeedbackList(List<String> data) throws FeedbackException{
        Integer num=feedbackDao.updateFeedbackById(data);
        if(num<SUCCESS_FLAG){
            throw new FeedbackException("删除历史反馈记录失败");
        }
    }

    @Override
    public Map<String,Object> getDetailedInf(String id) throws FeedbackException {
        //获取反馈内容
        Feedback feedback=feedbackDao.getDetailedInfById(id);
        //获取导员回复内容等
        List<Reply> list=replyDao.getReplyByFeedbackId(id);
        Map<String,Object> map=new HashMap<>();
        map.put("feedback",feedback);
        map.put("replyList",list);
        return map;
    }

    @Override
    public void updateDetail(String f,String id) throws FeedbackException{
        Integer num=feedbackDao.updateDetail(f,id);
        if(num<SUCCESS_FLAG){
            throw new FeedbackException("修改反馈内容失败");
        }
    }
}
