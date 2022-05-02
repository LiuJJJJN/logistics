package com.djtu.settings.dao;

import com.djtu.settings.pojo.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackDao {
    /**
     * 添加反馈
     * @param feedback 反馈实例
     * @return 影响条数
     */
    Integer studentAddFeedback(Feedback feedback);

    /**
     * 根据stu_id获取反馈列表
     * @param stuId
     * @return
     */
    List<Feedback> getFeedbackByStudent(@Param("stuId") String stuId,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

    /**
     * 获取stu_id为stuId的记录个数
     * @param stuId 学生id
     * @return 记录个数
     */
    Integer getListNum(String stuId);

    /**
     * 删除历史记录-实质是把标记改为1
     * @param list id列表
     * @return 更新的条数
     */
    Integer updateFeedbackById(List<String> list);

    /**
     * 根据id获取反馈
     * @param id 反馈主键
     * @return 反馈实例
     */
    Feedback getDetailedInfById(String id);

    /**
     * 更新反馈内容
     * @param feedback
     * @return
     */
    Integer updateDetail(@Param("feedback") String feedback,@Param("id")String id);
}
