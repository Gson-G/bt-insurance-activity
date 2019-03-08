package com.bz.ins.activity.prize.service;

import com.bz.ins.activity.prize.mapper.ActivityPrizeMapper;
import com.bz.ins.activity.prize.model.ActivityPrize;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 4:57 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class PrizeService {

    @Resource
    private ActivityPrizeMapper activityPrizeMapper;


    public ActivityPrize getByID(Integer id) {
        return activityPrizeMapper.selectById(id);
    }


}
