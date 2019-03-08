package com.bz.ins.activity.activity.strategy;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:35 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public abstract class DefaultActivityStrategy implements ActivityStrategy{


    /**
     * 缓存活动信息 不通活动可能缓存的不同
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo cacheActivity(ActivityParamBo activityParamBo) {
        return null;
    }

    /**
     * 初始化活动
     *
     * @param activityParamBo activityParamBo activityID 活动id seasonID 赛季id
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo initActivity(ActivityParamBo activityParamBo) {
        return null;
    }

    /**
     * 准备开始玩游戏
     *
     * @param activityParamBo@return
     */
    @Override
    public ActivityResultBo filter(ActivityParamBo activityParamBo) {
        return null;
    }

    /**
     * 准备开始玩游戏
     *
     * @param activityParamBo activityID 活动id seasonID 赛季id
     * @return
     */
    @Override
    public ActivityResultBo getReady(ActivityParamBo activityParamBo) {
        return null;
    }
}
