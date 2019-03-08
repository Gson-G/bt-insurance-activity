package com.bz.ins.activity.prizepool.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.prize.bo.ActivityPrizeBo;
import com.bz.ins.activity.prizepool.service.PrizePoolService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:10 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityPrizePoolDomain")
public class ActivityPrizePoolNativeDomain implements ActivityPrizePoolDomain {

    @Resource
    private PrizePoolService prizePoolService;


    @Override
    public ActivityPrizeBo getByID(Integer id) throws ActivityException {
        return BeanUtil.convert(prizePoolService.getByID(id), ActivityPrizeBo.class);
    }

    @Override
    public ActivityPrizeBo getByActivityAndSeasonID(Integer activity, Integer seasonID) throws ActivityException {
        return null;
    }
}
