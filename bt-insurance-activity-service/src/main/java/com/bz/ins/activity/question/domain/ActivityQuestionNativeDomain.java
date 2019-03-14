package com.bz.ins.activity.question.domain;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.answer.bo.ActivityAnswerBo;
import com.bz.ins.activity.answer.domain.AnswerDomain;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.joinrecord.domain.ActivityJoinRecordDomain;
import com.bz.ins.activity.question.bo.ActivityQuestionBo;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.bz.ins.activity.question.bo.ScoreResult;
import com.bz.ins.activity.question.calculate.CalculateScoreBean;
import com.bz.ins.activity.question.model.ActivityQuestion;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import com.bz.ins.activity.question.service.QuestionService;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.domain.ActivityRankDomain;
import com.bz.ins.activity.util.ExcelPoji;
import com.bz.ins.activity.util.ExcelUtil;
import com.bz.ins.common.utils.BeanUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    private AnswerDomain answerDomain;

    @Resource
    private ActivityRankDomain activityRankDomain;

    @Resource
    private ActivityJoinRecordDomain activityJoinRecordDomain;

    private final static String QUESTION_CONTENT = "%1$s的花名是什么";

    private final static String QUESTION_CONTENT_TWO = "%1$s是谁的花名";

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
        List<QuestionAnswerPojo> pojoList = questionService.getTestQuesttions(activity, season, 10);
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

    @Override
    public void save(ActivityQuestionBo answerBo) {
        questionService.save(BeanUtil.convert(answerBo, ActivityQuestion.class));
    }

    /**
     * 保存
     *
     * @param answerID
     */
    @Override
    public void updateRightAnswer(Integer answerID, Integer id) {
        questionService.updateRightAnswer(answerID, id);
    }

    @Override
    public void initAnswer() {

        File file = new File("/Users/kantenmei/Downloads/quest.xlsx");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            List<ExcelPoji> ls = ExcelUtil.parseExcel(fis, file.getName());
            List<String> nickNameList = ls.stream().map(ExcelPoji :: getNickName).collect(Collectors.toList());
            List<String> nameList = ls.stream().map(ExcelPoji :: getName).collect(Collectors.toList());
            System.out.println(ls.size());
            ls.forEach(t -> saveQuestion(t, nickNameList, nameList));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 运营个税改革获取题目的策略
     *
     * @param activityParamBo
     * @return
     * @throws ActivityException
     */
    @Override
    public List<QuestionAnswerBo> getQuestionForTaxGame(ActivityParamBo activityParamBo) throws ActivityException {
        Integer userID = activityParamBo.getUserID();
        ActivityRankBo activityRankBo = activityRankDomain.getByID(userID);
        Integer maxQuestion = null == activityRankBo ? 0 : activityRankBo.getMaxQuestionCode();
        List<QuestionAnswerPojo> pojoList = questionService.getQuestionForTaxGame(activityParamBo.getActivityID(),
                activityParamBo.getSeasonID(), maxQuestion, 5);
        return convertToBo(pojoList);
    }

    private void saveQuestion(ExcelPoji excelPoji, List<String> nickNameList, List<String> nameList) {
        ActivityQuestionBo activityQuestion = new ActivityQuestionBo.Builder()
                .activityID(1).season(1).seasonID(1).score(10).content(String.format(QUESTION_CONTENT, excelPoji.getName())).answerID(0)
                .weight(Boolean.TRUE.equals(excelPoji.getBoss()) ? 101 : 100).build();
        ActivityQuestion activityQuestion1 = BeanUtil.convert(activityQuestion, ActivityQuestion.class);
        questionService.save(activityQuestion1);
        Integer id = activityQuestion1.getID();
        ActivityAnswerBo activityAnswerBo = new ActivityAnswerBo.Builder().content(excelPoji.getNickName()).code("N")
                .questionID(id).score(10).build();
        Integer rightAnswer = answerDomain.save(activityAnswerBo);
        updateRightAnswer(rightAnswer, id);
        int i = 0;
        while (i < 3) {
            Set<String> resultSet = Sets.newHashSet();
            Random random = new Random();
            Integer hehe = random.nextInt(nickNameList.size() - 1);
            String nickName = nickNameList.get(hehe);
            if (!nickName.equals(excelPoji.getNickName()) && !resultSet.contains(nickName)) {
                ActivityAnswerBo activityAnswerBo1 = new ActivityAnswerBo.Builder().content(nickName).code("N")
                        .questionID(id).score(10).build();
                answerDomain.save(activityAnswerBo1);
                resultSet.add(nickName);
                i++;
            }
        }

        ActivityQuestionBo activityQuestion2 = new ActivityQuestionBo.Builder()
                .activityID(1).season(1).seasonID(1).score(10).content(String.format(QUESTION_CONTENT_TWO, excelPoji.getNickName())).answerID(0)
                .weight(Boolean.TRUE.equals(excelPoji.getBoss()) ? 101 : 100).build();
        ActivityQuestion activityQuestion3 = BeanUtil.convert(activityQuestion2, ActivityQuestion.class);
        questionService.save(activityQuestion3);
        Integer id2 = activityQuestion3.getID();
        ActivityAnswerBo activityAnswerBo1 = new ActivityAnswerBo.Builder().content(excelPoji.getName()).code("N")
                .questionID(id2).score(10).build();
        Integer rightAnswer1 = answerDomain.save(activityAnswerBo1);
        updateRightAnswer(rightAnswer1, id2);
        int i2 = 0;
        while (i2 < 3) {
            Set<String> resultSet = Sets.newHashSet();
            Random random = new Random();
            Integer hehe = random.nextInt(nickNameList.size() - 1);
            String name = nameList.get(hehe);
            if (!name.equals(excelPoji.getNickName()) && !resultSet.contains(name)) {
                ActivityAnswerBo activityAnswerBo4 = new ActivityAnswerBo.Builder().content(name).code("N")
                        .questionID(id2).score(10).build();
                answerDomain.save(activityAnswerBo4);
                resultSet.add(name);
                i2++;
            }
        }







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
