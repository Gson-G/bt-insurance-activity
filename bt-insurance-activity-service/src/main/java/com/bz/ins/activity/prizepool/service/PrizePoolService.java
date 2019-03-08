package com.bz.ins.activity.prizepool.service;

import com.bz.ins.activity.prizepool.mapper.ActivityPrizePoolMapper;
import com.bz.ins.activity.prizepool.model.ActivityPrizePool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:12 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class PrizePoolService {

    @Resource
    private ActivityPrizePoolMapper activityPrizePoolMapper;


    public ActivityPrizePool getByID(Integer id) {
        return activityPrizePoolMapper.selectById(id);
    }

}
