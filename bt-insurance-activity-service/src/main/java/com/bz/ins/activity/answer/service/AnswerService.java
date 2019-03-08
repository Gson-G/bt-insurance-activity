package com.bz.ins.activity.answer.service;

import com.bz.ins.activity.answer.mapper.ActivityAnswerMapper;
import com.bz.ins.activity.answer.model.ActivityAnswer;
import org.springframework.stereotype.Service;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:44 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class AnswerService {

    private ActivityAnswerMapper activityAnswerMapper;


    public ActivityAnswer getByID(Integer answerID) {
        return activityAnswerMapper.selectById(answerID);
    }

}
