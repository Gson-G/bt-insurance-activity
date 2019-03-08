package com.bz.ins.activity.season.mapper;

import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import com.bz.ins.activity.season.model.ActivitySeason;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ActivitySeasonMapper extends BaseMapper<ActivitySeason> {


    ActivitySeasonBo getCurrentSeason(Integer activityID);

}