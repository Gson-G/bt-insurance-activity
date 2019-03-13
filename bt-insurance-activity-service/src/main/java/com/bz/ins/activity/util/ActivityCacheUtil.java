package com.bz.ins.activity.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.activity.constant.ActivityContants;
import com.bz.ins.activity.activity.model.Activity;
import com.bz.ins.activity.activity.service.ActivityService;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.filter.bo.ActivityFilterBo;
import com.bz.ins.activity.filter.domain.ActivityFilterDomain;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import com.bz.ins.activity.season.service.ActivitySeasonService;
import com.bz.ins.common.utils.BeanUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 2:10 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class ActivityCacheUtil {

//    @Resource
//    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivitySeasonService activitySeasonService;
    @Resource
    private ActivityFilterDomain activityFilterDomain;


    private Map<String, String> cache = Maps.newConcurrentMap();

    /**
     * 获取活动信息 从缓存中
     * @param activityID
     * @param seasonID
     * @return
     */
    private ActivityBo getActivityMessageCache(Integer activityID, Integer seasonID) throws ActivityException {
        String key = ActivityContants.getActivityMessageCacheKey(activityID, seasonID);
        //String result = redisTemplate.opsForValue().get(key);
        ActivityBo result = getCache(key);
        if (null != result) {
            return result;
        }
        Activity activity = activityService.getByID(activityID);
        if (null == activity) {
            return null;
        }
        ActivityBo activityBo = BeanUtil.convert(activity, ActivityBo.class);
        activityBo.setSeasonID(seasonID);

        //限制条件放入缓存
        List<ActivityFilterBo> activityFilterBos = activityFilterDomain.getFilterByActivityIDAndSeasonID(activityID, seasonID);
        activityBo.setFilterBoList(activityFilterBos);

        ActivityResultBo<ActivityBo> activityResultBo = ActivityUtil.getStrategy(activityBo.getActivityStratey())
                .cacheActivity(new ActivityParamBo(activityBo, activityID, seasonID));
        if (ActivityResultBo.isSuccess(activityResultBo)) {
            //redisTemplate.opsForValue().set(key, JSON.toJSONString(activityResultBo.getObject()));
            putCache(activityResultBo.getObject(), key);
            return activityResultBo.getObject();
        }
        return null;
    }

    /**
     * 获取活动信息 从缓存中
     * @param activityCode
     * @return
     */
    public ActivityBo getActivityMessageCache(String activityCode) throws ActivityException {
        String key = ActivityContants.getActivityMessageCacheKey(activityCode);
        ActivityBo result = getCache(key);
        if (null != result) {
            return result;
        }
        Activity activity = activityService.getByCode(activityCode);
        if (null == activity) {
            return null;
        }
        ActivityBo activityBo = BeanUtil.convert(activity, ActivityBo.class);
        ActivitySeasonBo activitySeasonBo = activitySeasonService.getCurrentSeason(activityBo.getID());
        Integer seasonID =  null == activitySeasonBo ? null : activitySeasonBo.getID();

        //限制条件放入缓存
        if (null != seasonID) {
            List<ActivityFilterBo> activityFilterBos = activityFilterDomain
                    .getFilterByActivityIDAndSeasonID(activity.getID(),seasonID);
            activityBo.setFilterBoList(activityFilterBos);
        }

        ActivityResultBo<ActivityBo> activityResultBo = ActivityUtil.getStrategy(activityBo.getActivityStratey())
                .cacheActivity(new ActivityParamBo(activityBo, activity.getID(), seasonID));
        if (ActivityResultBo.isSuccess(activityResultBo)) {
            putCache(activityResultBo.getObject(), key);
            return activityResultBo.getObject();
        }
        return null;
    }

    private ActivityBo getCache(String key) {
        String result = cache.get(key);
        if (!StringUtils.isEmpty(result)) {
            CacheInfoPojo<ActivityBo> cacheInfoPojo = JSON.parseObject(result, new TypeReference<CacheInfoPojo<ActivityBo>>(){});
            Long currentTime = System.currentTimeMillis();
            if (cacheInfoPojo.getExpTime() < currentTime) {
                return null;
            }
            return cacheInfoPojo.getObject();
        }
        return null;
    }

    private void putCache(ActivityBo activityBo, String key) {
        Long exptime = System.currentTimeMillis() + 1000 * 60 * 1;
        CacheInfoPojo<ActivityBo> cacheInfoPojo = new CacheInfoPojo<>(exptime, activityBo);
        cache.put(key, JSON.toJSONString(cacheInfoPojo));
    }

    public ActivitySeasonBo getCurrentSeasonFromCache(Integer activityID) throws ActivityException {
        String key = ActivityContants.getActivitySeasonCahcheKey(activityID);
        //String result = redisTemplate.opsForValue().get(key);
        String result = cache.get(key);
        if (!StringUtils.isEmpty(result)) {
            CacheInfoPojo<ActivitySeasonBo> cacheInfoPojo = JSON.parseObject(result, new TypeReference<CacheInfoPojo<ActivitySeasonBo>>(){});
            Long currentTime = System.currentTimeMillis();
            if (cacheInfoPojo.getExpTime() < System.currentTimeMillis()) {
                return getFromDbThenCache(activityID);
            }
            ActivitySeasonBo activitySeasonBo = cacheInfoPojo.getObject();
            //活动还在进行中
            if (activitySeasonBo.getStartTime().getTime() < currentTime
                    && currentTime > activitySeasonBo.getEndTime().getTime()) {
                return activitySeasonBo;
            }
        }

        return getFromDbThenCache(activityID);
    }

    private ActivitySeasonBo getFromDbThenCache(Integer activityID) throws ActivityException{
        ActivitySeasonBo activitySeasonBo = activitySeasonService.getCurrentSeason(activityID);
        if (null != activitySeasonBo) {
            Long exptime = System.currentTimeMillis() + 1000 * 60 * 30;
            CacheInfoPojo<ActivitySeasonBo> cacheInfoPojo = new CacheInfoPojo<>(exptime, activitySeasonBo);
            cache.put(ActivityContants.getActivitySeasonCahcheKey(activityID), JSON.toJSONString(cacheInfoPojo));
            return activitySeasonBo;
        }
        return null;

    }

}
