package com.bz.ins.activity.season.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import com.bz.ins.activity.season.service.ActivitySeasonService;
import com.bz.ins.activity.util.ActivityCacheUtil;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:31 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class ActivitySeasonNativeDomain implements ActivitySeasonDomain{

    @Resource
    private ActivitySeasonService activitySeasonService;

    @Resource
    private ActivityCacheUtil activityCacheUtil;

    /**
     * 通过ID 获取记录
     *
     * @param id
     * @return
     */
    @Override
    public ActivitySeasonBo getByID(Integer id) throws ActivityException {
        return BeanUtil.convert(activitySeasonService.getByID(id), ActivitySeasonBo.class);
    }

    public ActivitySeasonBo getCurrentSeason(Integer activityID) throws ActivityException{

        return activityCacheUtil.getCurrentSeasonFromCache(activityID);
    }
}
