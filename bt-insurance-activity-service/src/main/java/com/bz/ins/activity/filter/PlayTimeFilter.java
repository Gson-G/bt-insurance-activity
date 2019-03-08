package com.bz.ins.activity.filter;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.filter.bo.ActivityFilterBo;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 2:51 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class PlayTimeFilter implements ActivityRoleFilter{


    /**
     * filter 处理游戏资格等问题
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo beforeGame(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo) {
        return ActivityResultBo.success();
    }

    /**
     * filter 处理完结游戏后问题
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo afterGame(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo) {
        return ActivityResultBo.success();
    }

    /**
     * filter 处理获奖资格等问题
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo beforePrize(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo) {
        return ActivityResultBo.success();
    }

    /**
     * filter 处理获奖之后的问题
     *
     * @param activityParamBo
     * @return
     */
    @Override
    public ActivityResultBo afterPrize(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo) {
        return ActivityResultBo.success();
    }
}
