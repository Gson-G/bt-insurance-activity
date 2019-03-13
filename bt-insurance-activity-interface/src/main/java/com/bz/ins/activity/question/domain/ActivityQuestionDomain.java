package com.bz.ins.activity.question.domain;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.question.bo.ActivityQuestionBo;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.bz.ins.activity.question.bo.ScoreResult;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:47 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityQuestionDomain {


    /**
     * 通过id获取问题
     * @param questionID
     * @return
     */
    ActivityQuestionBo getByID(Integer questionID) throws ActivityException;

    /**
     * 获取所有题目 初始化时使用
     * @return
     */
    List<QuestionAnswerBo> findAll();

    /**
     *
     * @return
     */
    List<QuestionAnswerBo> getQuestionForGame(Integer activity, Integer season) throws ActivityException;


    /**
     * 计算单题目分数
     * @param questionScoreBo
     * @return
     */
    ScoreResult calScore(QuestionScoreBo questionScoreBo) throws ActivityException;

    /**
     * 计算总分
     * @param questionScoreBo
     * @return
     */
    ScoreResult calScoreTotal(List<QuestionScoreBo> questionScoreBo) throws ActivityException;

    /**
     * 通过questionid获取 题目与正确答案
     * @param questionID
     * @return
     */
    QuestionAnswerBo getByQuestionID(Integer questionID) throws ActivityException;


    void save(ActivityQuestionBo answerBo);

    /**
     * 保存
     * @param answerID
     */
    void updateRightAnswer(Integer answerID, Integer id);


    void initAnswer();


    /**
     * 运营个税改革获取题目的策略
     * @param activityParamBo
     * @return
     * @throws ActivityException
     */
    List<QuestionAnswerBo> getQuestionForTaxGame(ActivityParamBo activityParamBo) throws ActivityException;






}
