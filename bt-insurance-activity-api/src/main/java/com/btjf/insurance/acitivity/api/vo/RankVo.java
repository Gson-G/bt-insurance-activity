package com.btjf.insurance.acitivity.api.vo;

import com.btjf.insurance.user.bo.UserBo;
import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.bo.UserRankBo;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 2:04 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
public class RankVo {

    private String userName;

    private Integer score;

    private Integer rank;

    private Boolean activityStarted;

    public RankVo(ActivityRankBo activityRankBo) {
        this.userName = activityRankBo.getUserName();
        this.score = activityRankBo.getTotalScore();
        this.rank = activityRankBo.getRank();
    }

    public RankVo(UserBo userBo, UserRankBo userRank, ActivityResultBo<ActivityBo> activityResultBo) {
        this.userName = userBo.getRealName();
        this.score = userRank.getTotolScore();
        this.rank = userRank.getRank();
        this.activityStarted = ActivityResultBo.isSuccess(activityResultBo) && isStartGame(activityResultBo.getObject());
    }

    private Boolean isStartGame(ActivityBo activityBo) {
        if (null == activityBo) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        return activityBo.getStartTime().getTime() < currentTime
                && activityBo.getEndTime().getTime() > currentTime;
    }

    public static List<RankVo> convertToList(List<ActivityRankBo> boList) {
        List<RankVo> voList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(boList)) {
            return voList;
        }
        boList.forEach(t -> {
            RankVo rankVo = new RankVo(t);
            voList.add(rankVo);

        });
        return voList;
    }





}
