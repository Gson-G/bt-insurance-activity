package com.bz.ins.activity.rank.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.mapper.ActivityRankMapper;
import com.bz.ins.activity.rank.model.ActivityRank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:24 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class ActivityRankService {

    @Resource
    private ActivityRankMapper activityRankMapper;


    public ActivityRank getByID(Integer id) {
        return activityRankMapper.selectById(id);
    }

    public void save(ActivityRank activityRank) {
         activityRankMapper.insert(activityRank);
    }

    public List<ActivityRankBo> getRankList(Integer activityID, Integer seasonID){
        return activityRankMapper.getRankList(activityID, seasonID);
    }

    public ActivityRank getByUserID(Integer userID, Integer activityID, Integer seasonID) {
        return activityRankMapper
                .selectOne(new QueryWrapper<ActivityRank>().lambda()
                        .eq(ActivityRank :: getUserID, userID).eq(ActivityRank :: getActivityID, activityID)
                        .eq(ActivityRank :: getSeasonID, seasonID)
                );
    }


    public Integer getUserRank(Integer totalScore, Integer activityID, Integer seasonID) {
        return activityRankMapper.getUserRank(totalScore, activityID, seasonID);
    }

    public List<ActivityRankBo> getSameScoreUserRankList(Integer totalScore, Integer activityID, Integer seasonID) {
        return activityRankMapper.getUserRankList(totalScore, activityID, seasonID);
    }


    public boolean updateRank(Integer id, Integer score) {
        return activityRankMapper.updateRank(id, score) > 1;
    }
}
