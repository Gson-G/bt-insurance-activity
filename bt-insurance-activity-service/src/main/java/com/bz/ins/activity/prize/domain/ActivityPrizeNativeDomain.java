package com.bz.ins.activity.prize.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.prize.bo.ActivityPrizeBo;
import com.bz.ins.activity.prize.service.PrizeService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 4:56 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityPrizeDomain")
public class ActivityPrizeNativeDomain implements ActivityPrizeDomain{

    @Resource
    private PrizeService prizeService;


    /**
     * 通过id获取奖品
     *
     * @param prizeID
     * @return 活动bo
     */
    @Override
    public ActivityPrizeBo getByID(Integer prizeID) throws ActivityException {
        return BeanUtil.convert(prizeService.getByID(prizeID), ActivityPrizeBo.class);
    }
}
