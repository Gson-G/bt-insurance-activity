package com.bz.ins.activity.question.domain;

import com.bz.ins.activity.answer.bo.ActivityAnswerBo;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.joinrecord.domain.ActivityJoinRecordDomain;
import com.bz.ins.activity.question.bo.ActivityQuestionBo;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.bz.ins.activity.question.bo.ScoreResult;
import com.bz.ins.activity.question.calculate.CalculateScoreBean;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import com.bz.ins.activity.question.service.QuestionService;
import com.bz.ins.common.utils.BeanUtil;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:50 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityQuestionDomain")
public class ActivityQuestionNativeDomain implements ActivityQuestionDomain {

    @Resource
    private QuestionService questionService;

    @Resource
    private CalculateScoreBean calculateScoreBean;

    @Resource
    private ActivityJoinRecordDomain activityJoinRecordDomain;

    /**
     * 通过id获取问题
     *
     * @param questionID
     * @return
     */
    @Override
    public ActivityQuestionBo getByID(Integer questionID) throws ActivityException {
        return BeanUtil.convert(questionService.getByID(questionID), ActivityQuestionBo.class);
    }

    /**
     * 获取所有题目 初始化时使用
     *
     * @return
     */
    @Override
    public List<QuestionAnswerBo> findAll() throws ActivityException {
        List<QuestionAnswerPojo> pojoList = questionService.queryAll();

        return convertToBo(pojoList);
    }

    /**
     * @return
     */
    @Override
    public List<QuestionAnswerBo> getQuestionForGame(Integer activity, Integer season) throws ActivityException {
        List<QuestionAnswerPojo> pojoList = questionService.getTestQuesttions(10);
        return convertToBo(pojoList);
    }

    /**
     * 计算单题目分数
     *
     * @param questionScoreBo
     * @return
     */
    @Override
    public ScoreResult calScore(QuestionScoreBo questionScoreBo) throws ActivityException {
        return calculateScoreBean.calScore(questionScoreBo);
    }

    /**
     * 计算总分
     *
     * @param questionScoreBo
     * @return
     */
    @Override
    public ScoreResult calScoreTotal(List<QuestionScoreBo> questionScoreBo) throws ActivityException {
        List<ScoreResult> resultList = questionScoreBo
                .stream().map(t -> calScore(t)).collect(Collectors.toList());

        int score = resultList.stream().map(ScoreResult :: getScore).reduce((sum, item) -> sum + item).get();
        return new ScoreResult.Builder().score(score).build();
    }

    /**
     * 通过questionid获取 题目与正确答案
     *
     * @param questionID
     * @return
     */
    @Override
    public QuestionAnswerBo getByQuestionID(Integer questionID) throws ActivityException {
        QuestionAnswerPojo answerPojo = questionService.queryByUserID(questionID);
        return convertToBo(answerPojo);
    }

    private List<QuestionAnswerBo> convertToBo(List<QuestionAnswerPojo> pojoList) {
        List<QuestionAnswerBo> boLists = Lists.newArrayList();
        if (CollectionUtils.isEmpty(pojoList)) {
            return boLists;
        }
        pojoList.stream().forEach(t -> boLists.add(convertToBo(t)));
        return boLists;
    }

    private QuestionAnswerBo convertToBo(QuestionAnswerPojo questionAnswerPojo) {
        QuestionAnswerBo answerBo = BeanUtil.convert(questionAnswerPojo, QuestionAnswerBo.class);
        List<ActivityAnswerBo> boList = BeanUtil.convertList(questionAnswerPojo.getAnswers(), ActivityAnswerBo.class);
        answerBo.setAnswers(boList);

        if (null != questionAnswerPojo.getRightAnswer()) {
            ActivityAnswerBo activityAnswerBo = BeanUtil.convert(questionAnswerPojo.getRightAnswer(), ActivityAnswerBo.class);
            activityAnswerBo.setScore(questionAnswerPojo.getScore());
            answerBo.setRightAnswer(activityAnswerBo);
            return answerBo;
        }
        //寻找正确答案
        ActivityAnswerBo rightAnswer = boList.stream().filter(t -> t.getID().equals(answerBo.getAnswerID())).findFirst().orElse(null);
        if (null != rightAnswer) {
            rightAnswer.setScore(questionAnswerPojo.getScore());
            answerBo.setRightAnswer(rightAnswer);
        }

        return answerBo;
    }
}
