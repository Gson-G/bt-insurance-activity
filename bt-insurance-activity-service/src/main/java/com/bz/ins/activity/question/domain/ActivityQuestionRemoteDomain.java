package com.bz.ins.activity.question.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.question.bo.ActivityQuestionBo;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.bz.ins.activity.question.bo.ScoreResult;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 2:49 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service(version = "1.0.0")
public class ActivityQuestionRemoteDomain implements ActivityQuestionDomain{

    @Resource
    private ActivityQuestionDomain activityQuestionDomain;

    /**
     * 通过id获取问题
     *
     * @param questionID
     * @return
     */
    @Override
    public ActivityQuestionBo getByID(Integer questionID) throws ActivityException {
        return activityQuestionDomain.getByID(questionID);
    }

    /**
     * 获取所有题目 初始化时使用
     *
     * @return
     */
    @Override
    public List<QuestionAnswerBo> findAll() throws ActivityException {
        return activityQuestionDomain.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<QuestionAnswerBo> getQuestionForGame(Integer activity, Integer season) throws ActivityException {
        return activityQuestionDomain.getQuestionForGame(activity, season);
    }

    /**
     * 计算单题目分数
     *
     * @param questionScoreBo
     * @return
     */
    @Override
    public ScoreResult calScore(QuestionScoreBo questionScoreBo) throws ActivityException {
        return activityQuestionDomain.calScore(questionScoreBo);
    }

    /**
     * 计算总分
     *
     * @param questionScoreBo
     * @return
     */
    @Override
    public ScoreResult calScoreTotal(List<QuestionScoreBo> questionScoreBo) throws ActivityException {
        return activityQuestionDomain.calScoreTotal(questionScoreBo);
    }

    /**
     * 通过questionid获取 题目与正确答案
     *
     * @param questionID
     * @return
     */
    @Override
    public QuestionAnswerBo getByQuestionID(Integer questionID) throws ActivityException {
        return activityQuestionDomain.getByQuestionID(questionID);
    }
}
