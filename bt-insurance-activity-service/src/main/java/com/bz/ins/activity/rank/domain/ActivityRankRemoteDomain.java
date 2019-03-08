package com.bz.ins.activity.rank.domain;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.bo.UserRankBo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 11:27 AM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service(version = "1.0.0")
public class ActivityRankRemoteDomain implements ActivityRankDomain{

    @Resource
    private ActivityRankDomain activityRankDomain;

    /**
     * id获取结果
     *
     * @param id
     * @return
     */
    @Override
    public ActivityRankBo getByID(Integer id) {
        return activityRankDomain.getByID(id);
    }

    /**
     * 保存记录
     *
     * @param activityRankBo
     */
    @Override
    public void save(ActivityRankBo activityRankBo) {

        activityRankDomain.save(activityRankBo);
    }

    /**
     * 排行榜
     *
     * @param activityID
     * @param seasonID
     * @return
     */
    @Override
    public List<ActivityRankBo> getRankList(Integer activityID, Integer seasonID) {
        return activityRankDomain.getRankList(activityID, seasonID);
    }

    /**
     * 获取用户排行
     *
     * @param userID
     * @return
     */
    @Override
    public UserRankBo getUserRank(Integer userID, Integer activityID, Integer seasonID) {
        return activityRankDomain.getUserRank(userID, activityID, seasonID);
    }

    /**
     * 更新活动rank
     *
     * @param activityParamBo
     * @throws ActivityException
     */
    @Override
    public void updateRank(ActivityParamBo activityParamBo) throws ActivityException {
        activityRankDomain.updateRank(activityParamBo);
    }
}
