package com.bz.ins.activity.activity.strategy.question;

import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.activity.service.ActivityService;
import com.bz.ins.activity.activity.strategy.DefaultActivityStrategy;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.domain.ActivityQuestionDomain;
import com.bz.ins.activity.question.service.QuestionService;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import com.bz.ins.activity.season.model.ActivitySeason;
import com.bz.ins.activity.season.service.ActivitySeasonService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/13
 * @time 2:12 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("taxQuestionActivity")
public class TaxQuestionActivityStrategy extends DefaultActivityStrategy {

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivitySeasonService activitySeasonService;

    @Resource
    private QuestionService questionService;

    @Resource
    private ActivityQuestionDomain activityQuestionDomain;

    @Resource
    private QuestionActvityStrategy questionActvityStrategy;

    /**
     * 缓存活动信息 不通活动可能缓存的不同
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo cacheActivity(ActivityParamBo activityParamBo) {
        return questionActvityStrategy.cacheActivity(activityParamBo);
    }

    /**
     * 初始化活动
     *
     * @param activityParamBo activityParamBo activityID 活动id seasonID 赛季id
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo initActivity(ActivityParamBo activityParamBo) {
        // List<QuestionAnswerPojo> pojoList = questionService.queryAll();


        return null;
    }


    /**
     * 准备开始玩游戏
     *
     * @param activityParamBo activityID 活动id seasonID 赛季id userID
     * @return
     */
    @Override
    public ActivityResultBo getReady(ActivityParamBo activityParamBo) {
        List<QuestionAnswerBo> boList = activityQuestionDomain
                .getQuestionForTaxGame(activityParamBo);

        return ActivityResultBo.success(boList);
    }

    /**
     * filter
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo filter(ActivityParamBo activityParamBo) {
        return null;
    }
}
