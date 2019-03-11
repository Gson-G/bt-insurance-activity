package com.bz.ins.activity.question.calculate;

import com.bz.ins.activity.answer.bo.ActivityAnswerBo;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.bz.ins.activity.question.bo.ScoreResult;
import com.bz.ins.activity.question.domain.ActivityQuestionDomain;
import com.google.common.collect.Maps;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 4:52 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class CalculateScoreBean implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private ActivityQuestionDomain activityQuestionDomain;

    /**
     * 题目的正确答案缓存在内存中
     */
    private TreeMap<Integer, ActivityAnswerBo> questionAnswerMap = Maps.newTreeMap();

    private static final Integer MAX_COST = 10;

    /**
     * 启动的时候把题库加载进来
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<QuestionAnswerBo> boList = activityQuestionDomain.findAll();
        Map<Integer, ActivityAnswerBo> allQuestion = boList.stream().collect(Collectors.toMap(QuestionAnswerBo :: getID, QuestionAnswerBo :: getRightAnswer));
        questionAnswerMap.putAll(allQuestion);
    }


    public ScoreResult calScore(QuestionScoreBo questionScoreBo) {
        ActivityAnswerBo activityAnswerBo = questionAnswerMap.get(questionScoreBo.getQuestionID());
        if (null == activityAnswerBo) {
            QuestionAnswerBo answerBo = activityQuestionDomain.getByQuestionID(questionScoreBo.getQuestionID());
            if (answerBo == null) {
                 new ScoreResult.Builder().score(0).right(false).build();
            }
            addToMap(answerBo);
            return calScore(answerBo.getRightAnswer(), questionScoreBo);
        }
        return calScore(activityAnswerBo, questionScoreBo);
    }

    /**
     * 判分啦
     * @param answerBo
     * @param questionScoreBo
     * @return
     */
    private ScoreResult calScore(ActivityAnswerBo answerBo, QuestionScoreBo questionScoreBo) {
        if (answerBo.getID().equals(questionScoreBo.getAnswerID())) {
            int cost = questionScoreBo.getCost() < 1000
                    ? 0 : (int) Math.ceil(questionScoreBo.getCost() / 1000d);
            cost = cost < MAX_COST ? cost : MAX_COST;
            int score = (int) ((MAX_COST - cost) * 0.1 * answerBo.getScore());
            return new ScoreResult.Builder().score(score).rightAnswerID(questionScoreBo.getAnswerID()).right(true).build();
        }
        return new ScoreResult.Builder().score(0).right(false).rightAnswerID(answerBo.getID()).build();
    }


    private void addToMap(QuestionAnswerBo answerBo) {
        synchronized (questionAnswerMap) {
            questionAnswerMap.putIfAbsent(answerBo.getID(), answerBo.getRightAnswer());
        }
    }
}
