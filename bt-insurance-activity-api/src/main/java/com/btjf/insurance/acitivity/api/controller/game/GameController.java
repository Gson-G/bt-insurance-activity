package com.btjf.insurance.acitivity.api.controller.game;

import com.btjf.application.util.XaResult;
import com.btjf.insurance.acitivity.api.controller.BaseController;
import com.btjf.insurance.acitivity.api.vo.*;
import com.btjf.insurance.user.bo.UserBo;
import com.btjf.insurance.user.domain.UserDomain;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.activity.domain.ActivityDomain;
import com.bz.ins.activity.joinrecord.bo.ActivityJoinRecordBo;
import com.bz.ins.activity.joinrecord.domain.ActivityJoinRecordDomain;
import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.bz.ins.activity.question.bo.ScoreResult;
import com.bz.ins.activity.question.domain.ActivityQuestionDomain;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.bo.UserRankBo;
import com.bz.ins.activity.rank.domain.ActivityRankDomain;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import com.bz.ins.activity.season.domain.ActivitySeasonDomain;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 4:22 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@RestController
@RequestMapping("/game")
public class GameController extends BaseController {

    private final static Integer ACTIVITYID = 1;


    @Reference(version = "1.0.0", timeout = 5000)
    private ActivityQuestionDomain activityQuestionDomain;

    @Reference(version = "1.0.0", timeout = 5000)
    private ActivityDomain activityDomain;

    @Reference(version = "1.0.0", timeout = 5000)
    private ActivityJoinRecordDomain activityJoinRecordDomain;

    @Reference(version = "1.0.0",timeout = 5000)
    private ActivityRankDomain activityRankDomain;

    @Reference(version = "1.0.0", timeout = 5000)
    private UserDomain userDomain;

    @Reference(version = "1.0.0", timeout = 5000)
    private ActivitySeasonDomain activitySeasonDomain;

    /**
     * 计算分数-单题
     * @param gameScoreVo
     * @return
     */
    @PostMapping("/calScore" )
    public XaResult<GameResultVo> calScore(@RequestBody GameScoreVo gameScoreVo) {

        QuestionScoreBo questionScoreBo = new QuestionScoreBo();

        questionScoreBo.setAnswerID(gameScoreVo.getOptionID());
        questionScoreBo.setQuestionID(gameScoreVo.getQuestionID());
        questionScoreBo.setCost(1000);
        ScoreResult scoreResult = activityQuestionDomain.calScore(questionScoreBo);
        GameResultVo gameResultVo = new GameResultVo(scoreResult);

        return XaResult.success(gameResultVo);
    }

    @GetMapping("/getQuestion")
    public XaResult<List<QuestionVo>> getQuestion() {
        ActivitySeasonBo activitySeasonBo = activitySeasonDomain.getCurrentSeason(ACTIVITYID);
        if (null == activitySeasonBo) {
            return XaResult.error("没有正在进行的活动");
        }
        ActivityParamBo activityParamBo = new ActivityParamBo.Builder()
                .activityID(ACTIVITYID).seasonID(activitySeasonBo.getID()).build();
        ActivityResultBo<List<QuestionAnswerBo>> questions = activityDomain.getReady(activityParamBo);
        List<QuestionAnswerBo> boList = questions.getObject();
        List<QuestionVo> voList = QuestionVo.convertToList(boList);
        return XaResult.success(voList);
    }

    @PostMapping("/total")
    public XaResult<GameResultVo> getTotal(@RequestBody CustomerAnswerListVo customerAnswerListVo) {
        ActivitySeasonBo activitySeasonBo = activitySeasonDomain.getCurrentSeason(ACTIVITYID);
        if (null == activitySeasonBo) {
            return XaResult.error("没有正在进行的活动");
        }
        UserBo userBo = getUser();
        List<QuestionScoreBo> boList = CustomerAnswerVo.converToList(customerAnswerListVo.getAnswers());
        ScoreResult scoreResult = activityQuestionDomain.calScoreTotal(boList);

        ActivityJoinRecordBo activityJoinRecordBo = new ActivityJoinRecordBo
                .Builder().activityID(ACTIVITYID).seasonID(activitySeasonBo.getID()).season(activitySeasonBo.getSeason())
                .createTime(new Date()).score(scoreResult.getScore()).userID(userBo.getID()).build();
        activityJoinRecordDomain.save(activityJoinRecordBo);
        //更新分数
        ActivityParamBo<Integer> activityParamBo = new ActivityParamBo.Builder().activityID(ACTIVITYID)
                .seasonID(activitySeasonBo.getID()).userID(userBo.getID())
                .season(activitySeasonBo.getSeason()).object(scoreResult.getScore()).userName(userBo.getRealName()).build();
        activityRankDomain.updateRank(activityParamBo);


        return XaResult.success(new GameResultVo(scoreResult));
    }

    @GetMapping("/rank")
    public XaResult<List<RankVo>> getRank() {
        ActivitySeasonBo activitySeasonBo = activitySeasonDomain.getCurrentSeason(ACTIVITYID);
        if (null == activitySeasonBo) {
            return XaResult.error("没有正在进行的活动");
        }
        List<ActivityRankBo> activityRankBoList = activityRankDomain.getRankList(ACTIVITYID, activitySeasonBo.getID());

        List<RankVo> rankVoList = RankVo.convertToList(activityRankBoList);

        return XaResult.success(rankVoList);
    }

    @GetMapping("/user")
    public XaResult<RankVo> getUserMessage() {
        ActivitySeasonBo activitySeasonBo = activitySeasonDomain.getCurrentSeason(ACTIVITYID);
        if (null == activitySeasonBo) {
            return XaResult.error("没有正在进行的活动");
        }
        UserRankBo userRank = activityRankDomain
                .getUserRank(getCurrentUserID(), ACTIVITYID, activitySeasonBo.getID());

        UserBo userBo = userDomain.get(getCurrentUserID());

        return XaResult.success(new RankVo(userBo, userRank));
    }

}
