package com.bz.ins.activity.activity.domain;

import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.activity.service.ActivityService;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.filter.bo.ActivityFilterBo;
import com.bz.ins.activity.util.ActivityCacheUtil;
import com.bz.ins.activity.util.ActivityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 1:44 PM
 * @function 功能：活动相关方法
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityDomain")
public class ActivityNativeDomain implements ActivityDomain{


    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityCacheUtil activityCacheUtil;

    /**
     * 初始化活动
     * @param activityID 活动id
     * @param seasonID 赛季id
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo initActivity(Integer activityID, Integer seasonID) throws ActivityException {
        ActivityBo activityBo = activityCacheUtil.getActivityMessageCache(activityID, seasonID);
        if (null == activityBo) {
            return ActivityResultBo.fail();
        }
        return ActivityUtil.getStrategy(activityBo.getActivityStratey()).initActivity(
                new ActivityParamBo.Builder<ActivityBo>().activityID(activityID).seasonID(seasonID).build());
    }

    /**
     * 获取奖品
     *
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo getPrize(ActivityParamBo activityParamBo) throws ActivityException {
        return null;
    }

    /**
     * 游戏准备
     *
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo getReady(ActivityParamBo activityParamBo) throws ActivityException {
        ActivityBo activityBo = activityCacheUtil
                .getActivityMessageCache(activityParamBo.getActivityID(), activityParamBo.getSeasonID());
        if (null == activityBo) {
            return ActivityResultBo.fail();
        }
        //校验活动
        volidateActivity(activityBo, activityParamBo);

        return ActivityUtil.getStrategy(activityBo.getActivityStratey()).getReady(activityParamBo);
    }

    /**
     * 玩游戏方法
     *
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo play(ActivityParamBo activityParamBo) throws ActivityException {

        return null;
    }

    /**
     * 获取具体活动基本信息
     *
     * @param activityID 活动id
     * @param seasonID   赛季id
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo getActivityMessage(Integer activityID, Integer seasonID) throws ActivityException {
        ActivityBo activityBo = activityCacheUtil
                .getActivityMessageCache(activityID, seasonID);
        return ActivityResultBo.success(activityBo);
    }

    /**
     * 通过活动id 获取当前正在进行的期数等信息
     *
     * @param activityID 活动id
     * @return ActivityResultBo
     */
    @Override
    public ActivityResultBo getCurrentActivity(Integer activityID) throws ActivityException {
        return null;
    }

    /**
     * 校验活动是否能进行
     *
     * @param activityBo
     * @param activityParamBo
     * @throws ActivityException
     */
    @Override
    public void volidateActivity(ActivityBo activityBo, ActivityParamBo activityParamBo) throws ActivityException {
        long nowTime = System.currentTimeMillis();
        if (nowTime < activityBo.getStartTime().getTime()
                || nowTime > activityBo.getEndTime().getTime()) {
            throw new ActivityException("不在活动时间呢");
        }
        if (nowTime < activityBo.getActivitySeasonBo().getStartTime().getTime()
                || nowTime > activityBo.getActivitySeasonBo().getEndTime().getTime()) {
            throw new ActivityException("不在本期活动时间呢");
        }
        List<ActivityFilterBo> boList = activityBo.getFilterBoList();
        //todo filter 检验活动
    }

}
