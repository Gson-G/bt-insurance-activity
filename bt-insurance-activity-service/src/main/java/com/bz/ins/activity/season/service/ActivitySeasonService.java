package com.bz.ins.activity.season.service;

import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import com.bz.ins.activity.season.mapper.ActivitySeasonMapper;
import com.bz.ins.activity.season.model.ActivitySeason;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:33 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class ActivitySeasonService {

    @Resource
    private ActivitySeasonMapper activitySeasonMapper;

    public ActivitySeason getByID(Integer id) {
        return activitySeasonMapper.selectById(id);
    }

    public ActivitySeasonBo getCurrentSeason(Integer activityID) {
        return activitySeasonMapper.getCurrentSeason(activityID);
    }

}
