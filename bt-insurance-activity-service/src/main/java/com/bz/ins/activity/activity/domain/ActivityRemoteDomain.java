package com.bz.ins.activity.activity.domain;

import com.bz.ins.activity.activity.bo.ActivityBo;
import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.activity.bo.ActivityResultBo;
import com.bz.ins.activity.exception.ActivityException;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 10:53 AM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service(version = "1.0.0")
public class ActivityRemoteDomain implements ActivityDomain{

    @Resource
    private ActivityDomain activityDomain;


    /**
     * 初始化活动
     *
     * @param activityParamBo 活动请求参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo initActivity(ActivityParamBo activityParamBo) throws ActivityException {
        return activityDomain.initActivity(activityParamBo);
    }

    /**
     * 获取奖品
     *
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo getPrize(ActivityParamBo activityParamBo) throws ActivityException {
        return activityDomain.getPrize(activityParamBo);
    }

    /**
     * 游戏准备
     *
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo getReady(ActivityParamBo activityParamBo) throws ActivityException {
        return activityDomain.getReady(activityParamBo);
    }

    /**
     * 玩游戏方法
     *
     * @param activityParamBo 活动请求统一参数
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo play(ActivityParamBo activityParamBo) throws ActivityException {
        return activityDomain.play(activityParamBo);
    }

    /**
     * 获取具体活动基本信息
     *
     * @param activityCode 活动code
     * @param seasonID     赛季id
     * @return 统一返回类型
     */
    @Override
    public ActivityResultBo getActivityMessage(String activityCode, Integer seasonID) throws ActivityException {
        return activityDomain.getActivityMessage(activityCode, seasonID);
    }

    /**
     * 通过活动id 获取当前正在进行的期数等信息
     *
     * @param activityCode 活动code
     * @return ActivityResultBo
     */
    @Override
    public ActivityResultBo getCurrentActivity(String activityCode) throws ActivityException {
        return activityDomain.getCurrentActivity(activityCode);
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
        activityDomain.volidateActivity(activityBo, activityParamBo);
    }
}
