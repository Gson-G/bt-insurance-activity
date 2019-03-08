package com.bz.ins.activity.prizepool.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.prize.bo.ActivityPrizeBo;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:08 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityPrizePoolDomain {


    ActivityPrizeBo getByID(Integer id) throws ActivityException;


    ActivityPrizeBo getByActivityAndSeasonID(Integer activity, Integer seasonID) throws ActivityException;


}
