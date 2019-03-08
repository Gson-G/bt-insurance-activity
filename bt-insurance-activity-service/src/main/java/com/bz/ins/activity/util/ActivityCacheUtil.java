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
    public ActivityBo getActivityMessageCache(Integer activityID, Integer seasonID) throws ActivityException {
        String key = ActivityContants.getActivityMessageCacheKey(activityID, seasonID);
        //String result = redisTemplate.opsForValue().get(key);
        String result = cache.get(key);
        if (!StringUtils.isEmpty(result)) {
            return JSON.parseObject(result, new TypeReference<ActivityBo>(){});
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
            cache.put(key, JSON.toJSONString(activityResultBo.getObject()));
            return activityResultBo.getObject();
        }
        return null;
    }

    public ActivitySeasonBo getCurrentSeasonFromCache(Integer activityID) throws ActivityException {
        String key = ActivityContants.getActivitySeasonCahcheKey(activityID);
        //String result = redisTemplate.opsForValue().get(key);
        String result = cache.get(key);
        if (!StringUtils.isEmpty(result)) {
            CacheInfoPojo<ActivitySeasonBo> cacheInfoPojo = JSON.parseObject(result, new TypeReference<CacheInfoPojo<ActivitySeasonBo>>(){});
            Long currentTime = System.currentTimeMillis();
            if (cacheInfoPojo.getExpTime() < System.currentTimeMillis()) {
                activitySeasonService.getCurrentSeason(activityID);
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
            Long exptime = System.currentTimeMillis() + 1000 * 1000 * 3600 * 24;
            CacheInfoPojo<ActivitySeasonBo> cacheInfoPojo = new CacheInfoPojo<>(exptime, activitySeasonBo);
            cache.put(ActivityContants.getActivitySeasonCahcheKey(activityID), JSON.toJSONString(cacheInfoPojo));
            return activitySeasonBo;
        }
        throw new ActivityException("没有正在进行的活动");

    }

}
