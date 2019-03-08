package com.bz.ins.activity.rank.domain;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.bo.UserRankBo;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:16 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityRankDomain {

    /**
     * id获取结果
     * @param id
     * @return
     */
    ActivityRankBo getByID(Integer id) throws ActivityException;

    /**
     * 保存记录
     * @param activityRankBo
     */
    void save(ActivityRankBo activityRankBo) throws ActivityException;

    /**
     * 排行榜
     * @return
     */
    List<ActivityRankBo>  getRankList(Integer activityID, Integer seasonID) throws ActivityException;

    /**
     * 获取用户排行
     * @param userID
     * @return
     */
    UserRankBo getUserRank(Integer userID, Integer activityID, Integer seasonID) throws ActivityException;

    /**
     * 更新活动rank
     * @param activityParamBo
     * @throws ActivityException
     */
    void updateRank(ActivityParamBo activityParamBo) throws ActivityException;


}
