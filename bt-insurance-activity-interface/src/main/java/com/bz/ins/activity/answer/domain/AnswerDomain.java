package com.bz.ins.activity.answer.domain;

import com.bz.ins.activity.answer.bo.ActivityAnswerBo;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:40 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface AnswerDomain {


    /**
     * 通过答案id获取帐号
     * @param answerID
     * @return 答案bo
     */
    ActivityAnswerBo getAnswerByID(Integer answerID);


    /**
     * 通过题目id 获取所有答案选项
     * @param questionID
     * @return
     */
    List<ActivityAnswerBo> findByQuestionID(Integer questionID);

    /**
     *
     * 保存答案
     * @param activityAnswerBo
     * @return
     */
    Integer save(ActivityAnswerBo activityAnswerBo);


}
