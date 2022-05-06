package com.djtu.feedback.service.serviceImpl;

import com.djtu.exception.ReplyException;
import com.djtu.feedback.dao.FeedbackDao;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.feedback.pojo.Feedback;
import com.djtu.feedback.pojo.Reply;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.User;
import com.djtu.feedback.pojo.vo.FeedbackStudentVo;
import com.djtu.feedback.service.TutorReplyService;
import com.djtu.utils.DateTimeUtil;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TutorReplyServiceImpl implements TutorReplyService {

    @Autowired
    UserDao userDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    FeedbackDao feedbackDao;
    private static final Integer SUCCESS_FLAG = 1;

    @Override
    public Map<String, Object> getUnNum(User user) {
        String id = user.getId();
        //获取导员id
        //根据用户id查询导员id
        String tutorId = userDao.getTutorIdByUserId(id);
        //在学生表中查询stu_id是tutorId的学生id记录
        List<Student> stuList = studentDao.getStudentListByTutorId(tutorId);
        //根据学生id获取反馈表理的未反馈的记录数
        Integer total = feedbackDao.getNum(stuList);
        Map<String, Object> map = new HashMap<>();
        map.put("num", total);
        return map;
    }

    @Override
    public Map<String, Object> getUnReplyList(User user) {
        int pageNo = Integer.valueOf(user.getTutorId());
        int pageSize = Integer.valueOf(user.getAdminId());
        String id = user.getId();
        //根据用户id查询导员id
        String tutorId = userDao.getTutorIdByUserId(id);
        //在学生表中查询stu_id是tutorId的学生id记录
        List<Student> stuList = studentDao.getStudentListByTutorId(tutorId);
        //根据学生id获取反馈表里的id、titleFeed和time以及student表里的名字,条件是未反馈的
        List<FeedbackStudentVo> feedbackStudentList = feedbackDao.getFeedbackAndStuListByStuId(pageNo, pageSize, stuList);
        //根据学生id获取反馈表理的未反馈的记录数
        Integer total = feedbackDao.getNum(stuList);
        Map<String, Object> map = new HashMap<>();
        map.put("list", feedbackStudentList);
        map.put("total", total);
        return map;
    }

    @Override
    public Feedback getFeedbackInfById(String id) {
        Feedback feedback = feedbackDao.getFeedbackById(id);
        return feedback;
    }

    @Override
    @Transactional(rollbackFor = {ReplyException.class})
    public void tutorReply(Reply reply) throws ReplyException {
        String id = reply.getId();//userid
        String feedbackId = reply.getTutorId();//反馈id
        String replyInf = reply.getReply();//反馈内容
        //根据用户id查询导员id
        String tutorId = userDao.getTutorIdByUserId(id);
        Reply reply1 = new Reply();
        reply1.setId(StringUtil.generateUUID());
        reply1.setReply(replyInf);
        reply1.setTutorId(tutorId);
        reply1.setFeedbackId(feedbackId);
        reply1.setTime(DateTimeUtil.getSysTime());
        Integer num = feedbackDao.setReply(reply1);
        if (num < SUCCESS_FLAG) {
            throw new ReplyException("回复失败");
        }
        //将学生反馈状态设置为3（已回复状态）
        Integer n = feedbackDao.setStatus(feedbackId);
        if (n < SUCCESS_FLAG) {
            throw new ReplyException("回复失败");
        }
    }

    @Override
    public Map<String, Object> getReplyList(User user) {
        String id = user.getId();
        Integer pageNo = Integer.valueOf(user.getTutorId());
        Integer pageSize = Integer.valueOf(user.getAdminId());
        //获取导员id
        //根据用户id查询导员id
        String tutorId = userDao.getTutorIdByUserId(id);
        //在学生表中查询stu_id是tutorId的学生id记录
        List<Student> stuList = studentDao.getStudentListByTutorId(tutorId);
        //根据stuId获取feedback，条件导员回复过以及没有被删除的
        List<FeedbackStudentVo> list = feedbackDao.getFeedbackByStuIdWhereStatusDelFlag(pageNo, pageSize, stuList);
        Integer total = feedbackDao.getReplyHistoryNum(stuList);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return map;
    }

    @Override
    public Feedback getHistoryInfById(Feedback feedback) {
        String id = feedback.getId();
        Feedback feedback1 = feedbackDao.getHistoryInfById(id);
        return feedback1;
    }

    @Override
    public List<Reply> getReplyInfById(Feedback feedback) {
        String id = feedback.getId();
        List<Reply> list = feedbackDao.getReplyInfById(id);
        return list;
    }

    @Override
    @Transactional(rollbackFor = {ReplyException.class})
    public void replyAgain(Reply reply) throws ReplyException{
        String userId=reply.getTutorId();
        String tutorId = userDao.getTutorIdByUserId(userId);
        reply.setId(StringUtil.generateUUID());
        reply.setTime(DateTimeUtil.getSysTime());
        reply.setTutorId(tutorId);
        Integer n=feedbackDao.setReply(reply);
        if(n<SUCCESS_FLAG){
            throw new ReplyException("再次回复失败");
        }
    }
}
