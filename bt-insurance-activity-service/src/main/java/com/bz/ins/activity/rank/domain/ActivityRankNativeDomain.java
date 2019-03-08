package com.bz.ins.activity.rank.domain;

import com.bz.ins.activity.activity.bo.ActivityParamBo;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.bo.UserRankBo;
import com.bz.ins.activity.rank.model.ActivityRank;
import com.bz.ins.activity.rank.service.ActivityRankService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 5:22 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityRankDomain")
public class ActivityRankNativeDomain implements ActivityRankDomain{

    @Resource
    private ActivityRankService activityRankService;


    /**
     * id获取结果
     *
     * @param id
     * @return
     */
    @Override
    public ActivityRankBo getByID(Integer id) throws ActivityException {
        return BeanUtil.convert(activityRankService.getByID(id), ActivityRankBo.class);
    }

    /**
     * 保存记录
     *
     * @param activityRankBo
     */
    @Override
    public void save(ActivityRankBo activityRankBo) throws ActivityException {
        activityRankService.save(BeanUtil.convert(activityRankBo, ActivityRank.class));
    }

    /**
     * 排行榜
     *
     * @return
     */
    @Override
    public List<ActivityRankBo> getRankList(Integer activityID, Integer seasonID) throws ActivityException {
        return activityRankService.getRankList(activityID, seasonID);
    }

    /**
     * 获取用户排行
     *
     * @param userID
     * @return
     */
    @Override
    public UserRankBo getUserRank(Integer userID, Integer activityID, Integer seasonID) throws ActivityException {
        ActivityRank activityRank = activityRankService.getByUserID(userID, activityID, seasonID);
        if (null != activityRank) {
            Integer rank = activityRankService.getUserRank(activityRank.getTotalScore(), activityID, seasonID);
            return new UserRankBo(rank, activityRank.getTotalScore());
        }
        return new UserRankBo(0, 0);
    }

    /**
     * 更新活动rank
     *
     * @param activityParamBo
     * @throws ActivityException
     */
    @Override
    public void updateRank(ActivityParamBo activityParamBo) throws ActivityException {
        notNull(activityParamBo.getUserID(), "用户id为空");
        notNull(activityParamBo.getActivityID(), "活动id为空");
        notNull(activityParamBo.getSeasonID(), "活动期数id为空");
        ActivityRank activityRank = activityRankService.getByUserID(activityParamBo.getUserID(),
                activityParamBo.getActivityID(), activityParamBo.getSeasonID());
        if (null == activityRank) {
            //todo 加锁然后save
            saveRank(activityParamBo);
        }
        activityRankService.updateRank(activityRank.getUserID(), (Integer) activityParamBo.getObject());
    }

    private void saveRank(ActivityParamBo<Integer> activityParamBo) {
        ActivityRankBo activityRankBo = new ActivityRankBo.Builder().activityID(activityParamBo.getActivityID())
                .seasonID(activityParamBo.getSeasonID()).lastScore(0).totalScore(activityParamBo.getObject())
                .userID(activityParamBo.getUserID()).userName(activityParamBo.getUserName()).build();
        activityRankService.save(BeanUtil.convert(activityRankBo, ActivityRank.class));
    }

    public void notNull(Object object, String message) {
        if (null == object) {
            throw new ActivityException(message);
        }
    }
}
