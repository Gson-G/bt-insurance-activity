package com.bz.ins.activity.question.service;

import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.mapper.ActivityQuestionMapper;
import com.bz.ins.activity.question.model.ActivityQuestion;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:51 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class QuestionService {

    @Resource
    private ActivityQuestionMapper activityQuestionMapper;



    public ActivityQuestion getByID(Integer id) {
        return activityQuestionMapper.selectById(id);
    }



    public List<QuestionAnswerPojo> queryAll() {
        return activityQuestionMapper.findAll(null);
    }

    public QuestionAnswerPojo queryByUserID(Integer questionID) {
        List<QuestionAnswerPojo> pojos = activityQuestionMapper.findAll(questionID);
        return pojos.stream().findFirst().orElse(null);
    }

    /**
     * 获取考试题目
     * @return
     */
    public List<QuestionAnswerPojo> getTestQuesttions(Integer activityID, Integer seasonID, Integer number) {
        List<QuestionAnswerPojo> questionAnswerPojos = activityQuestionMapper.findQuestionForGameIds(activityID, seasonID, number);
        if (CollectionUtils.isEmpty(questionAnswerPojos)) {
            return Lists.newArrayList();
        }
        List<Integer> idList = questionAnswerPojos.stream().map(QuestionAnswerPojo :: getID).collect(Collectors.toList());
        return activityQuestionMapper.findQuestionForGame(idList);
    }

    public void save(ActivityQuestion question) {
        activityQuestionMapper.insert(question);
    }

    public void updateRightAnswer(Integer answerID, Integer id) {
        activityQuestionMapper.updateRightAnswer(answerID, id);
    }

    public List<QuestionAnswerPojo> getQuestionForTaxGame(Integer activityID, Integer seasonID, Integer maxQuestion, Integer number) {
        List<QuestionAnswerPojo> questionAnswerPojos = activityQuestionMapper
                .findQuestionForTaxGameIds(activityID, seasonID, maxQuestion, number);
        if (CollectionUtils.isEmpty(questionAnswerPojos)) {
            return Lists.newArrayList();
        }
        List<Integer> idList = questionAnswerPojos.stream().map(QuestionAnswerPojo :: getID).collect(Collectors.toList());
        return activityQuestionMapper.findQuestionForGame(idList);
    }
}
