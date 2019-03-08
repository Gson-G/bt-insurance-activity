package com.bz.ins.activity.prize.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.prize.bo.ActivityPrizeBo;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 4:54 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityPrizeDomain {

    /**
     * 通过id获取奖品
     * @param prizeID
     * @return 活动bo
     */
    ActivityPrizeBo getByID(Integer prizeID) throws ActivityException;

}
