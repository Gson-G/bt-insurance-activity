package com.bz.ins.activity.filter.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.filter.bo.ActivityFilterBo;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 4:20 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityFilterDomain {


    /**
     * 获取本期活动的filter
     * @param activityId
     * @param seasonID
     * @return
     */
    List<ActivityFilterBo> getFilterByActivityIDAndSeasonID(Integer activityId, Integer seasonID) throws ActivityException;


}
