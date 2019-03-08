package com.bz.ins.activity.activity.strategy;

import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.exception.ActivityException;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 3:08 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityStrategy {


    /**
     * 缓存活动信息 不通活动可能缓存的不同
     * @param activityParamBo<ActivityBo></> 统一请求参数
     * @return ActivityResultBo
     */
    ActivityResultBo cacheActivity(ActivityParamBo activityParamBo) throws ActivityException;


    /**
     * 初始化活动

     * @param activityParamBo activityParamBo activityID 活动id seasonID 赛季id
     * @return 统一返回类型
     */
    ActivityResultBo initActivity(ActivityParamBo activityParamBo) throws ActivityException;

    /**
     * 准备开始玩游戏
     * @param activityParamBo<User></> activityID 活动id seasonID 赛季id userID 用户id 等
     * @return
     */
    ActivityResultBo filter(ActivityParamBo activityParamBo) throws ActivityException;

    /**
     * 准备开始玩游戏
     * @param activityParamBo activityID 活动id seasonID 赛季id
     * @return
     */
    ActivityResultBo getReady(ActivityParamBo activityParamBo) throws ActivityException;




}
