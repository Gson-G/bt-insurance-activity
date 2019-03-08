package com.bz.ins.activity.season.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 7:20 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service(version = "1.0.0")
public class ActivitySeasonRemoteDomain implements ActivitySeasonDomain{

    @Resource
    private ActivitySeasonDomain activitySeasonDomain;

    /**
     * 通过ID 获取记录
     *
     * @param id
     * @return
     */
    @Override
    public ActivitySeasonBo getByID(Integer id) throws ActivityException {
        return activitySeasonDomain.getByID(id);
    }

    /**
     * 获取游戏当前期数
     *
     * @param activityID
     * @return
     */
    @Override
    public ActivitySeasonBo getCurrentSeason(Integer activityID) throws ActivityException {
        return activitySeasonDomain.getCurrentSeason(activityID);
    }
}
