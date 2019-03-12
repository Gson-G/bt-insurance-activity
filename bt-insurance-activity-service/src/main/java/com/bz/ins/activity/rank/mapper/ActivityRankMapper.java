package com.bz.ins.activity.rank.mapper;

import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.model.ActivityRank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityRankMapper extends BaseMapper<ActivityRank> {



    List<ActivityRankBo> getRankList(@Param("activityID") Integer activityID, @Param("seasonID") Integer seasonID);

    Integer getUserRank(@Param("totalScore")Integer totalScore, @Param("activityID") Integer activityID, @Param("seasonID") Integer seasonID);

    Integer updateRank(@Param("id")Integer id, @Param("score")Integer score);

    List<ActivityRankBo> getUserRankList(@Param("totalScore")Integer totalScore, @Param("activityID") Integer activityID, @Param("seasonID") Integer seasonID);
}