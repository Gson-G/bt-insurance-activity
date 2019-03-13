package com.bz.ins.activity.activity.domain;

import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.exception.ActivityException;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 10:08 AM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityDomain {


    /**
     * 初始化活动
     * @param activityParamBo 活动请求参数
     * @return 统一返回类型
     */
    ActivityResultBo initActivity(ActivityParamBo activityParamBo) throws ActivityException;

    /**
     * 获取奖品
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    ActivityResultBo getPrize(ActivityParamBo activityParamBo) throws ActivityException;


    /**
     * 游戏准备
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    ActivityResultBo getReady(ActivityParamBo activityParamBo) throws ActivityException;

    /**
     * 玩游戏方法
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    ActivityResultBo play(ActivityParamBo activityParamBo) throws ActivityException;

    /**
     * 获取具体活动基本信息
     * @param activityCode 活动code
     * @param seasonID 赛季id
     * @return 统一返回类型
     */
    ActivityResultBo getActivityMessage(String activityCode, Integer seasonID) throws ActivityException;


    /**
     * 通过活动id 获取当前正在进行的期数等信息
     * @param activityCode 活动code
     * @return  ActivityResultBo
     */
    ActivityResultBo getCurrentActivity(String activityCode) throws ActivityException;

    /**
     *
     * 校验活动是否能进行
     * @param activityBo
     * @param activityParamBo
     * @throws ActivityException
     */
    void volidateActivity(ActivityBo activityBo, ActivityParamBo activityParamBo) throws ActivityException;






}
