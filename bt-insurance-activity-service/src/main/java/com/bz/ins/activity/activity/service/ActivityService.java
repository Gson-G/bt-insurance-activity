package com.bz.ins.activity.activity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bz.ins.activity.activity.mapper.ActivityMapper;
import com.bz.ins.activity.activity.model.Activity;
import com.bz.ins.activity.rank.model.ActivityRank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 1:47 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class ActivityService {

    @Resource
    private ActivityMapper activityMapper;


    public Activity getByID(Integer activityID) {
        return activityMapper.selectById(activityID);
    }

    public Activity getByCode(String activityCode) {
        return activityMapper.selectOne(new QueryWrapper<Activity>().lambda()
                .eq(Activity :: getCode, activityCode));
    }


}
