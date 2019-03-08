package com.bz.ins.activity.filter.domain;

import com.bz.ins.activity.filter.bo.ActivityFilterBo;
import com.bz.ins.activity.filter.model.ActivityFilter;
import com.bz.ins.activity.filter.service.FilterService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 4:27 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityFilterDomain")
public class ActivityFilterNativeDomain implements ActivityFilterDomain{


    @Resource
    private FilterService filterService;

    /**
     * 获取本期活动的filter
     *
     * @param activityId
     * @param seasonID
     * @return
     */
    @Override
    public List<ActivityFilterBo> getFilterByActivityIDAndSeasonID(Integer activityId, Integer seasonID) {
        return BeanUtil.convertList(filterService.findByActivityIDAndSeasonID(activityId, seasonID), ActivityFilterBo.class);
    }
}
