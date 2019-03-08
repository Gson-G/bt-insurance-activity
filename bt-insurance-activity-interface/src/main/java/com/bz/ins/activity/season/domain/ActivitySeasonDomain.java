package com.bz.ins.activity.season.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:30 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivitySeasonDomain {


    /**
     * 通过ID 获取记录
     * @param id
     * @return
     */
    ActivitySeasonBo getByID(Integer id) throws ActivityException;


    /**
     * 获取游戏当前期数
     * @param activityID
     * @return
     */
    ActivitySeasonBo getCurrentSeason(Integer activityID) throws ActivityException;

}
