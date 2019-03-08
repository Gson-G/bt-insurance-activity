package com.bz.ins.activity.answer.domain;

import com.bz.ins.activity.answer.bo.ActivityAnswerBo;
import com.bz.ins.activity.answer.service.AnswerService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:43 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class AnswerNativeDomain implements AnswerDomain{


    private AnswerService answerService;

    /**
     * 通过答案id获取帐号
     *
     * @param answerID
     * @return 答案bo
     */
    @Override
    public ActivityAnswerBo getAnswerByID(Integer answerID) {
        if (null == answerID) return null;
        return BeanUtil.convert(answerService.getByID(answerID), ActivityAnswerBo.class);
    }

    /**
     * 通过题目id 获取所有答案选项
     *
     * @param questionID
     * @return
     */
    @Override
    public List<ActivityAnswerBo> findByQuestionID(Integer questionID) {
        return null;
    }
}
