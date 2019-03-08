package com.bz.ins.activity.filter;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.filter.bo.ActivityFilterBo;

/**
 *  filter 处理玩游戏前的校验的等问题
 * @author kantenmei
 * @date 2019/3/5
 * @time 2:42 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityRoleFilter {


    /**
     * filter 处理游戏资格等问题
     * @param activityParamBo
     * @return
     */
    ActivityResultBo beforeGame(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo);

    /**
     * filter 处理完结游戏后问题
     * @param activityParamBo
     * @return
     */
    ActivityResultBo afterGame(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo);


    /**
     * filter 处理获奖资格等问题
     * @param activityParamBo
     * @return
     */
    ActivityResultBo beforePrize(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo);

    /**
     * filter 处理获奖之后的问题
     * @param activityParamBo
     * @return
     */
    ActivityResultBo afterPrize(ActivityParamBo activityParamBo, ActivityFilterBo activityFilterBo);

}
