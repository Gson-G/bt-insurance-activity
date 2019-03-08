package com.bz.ins.activity.joinrecord.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.joinrecord.bo.ActivityJoinRecordBo;
import com.bz.ins.activity.joinrecord.model.ActivityJoinRecord;
import com.bz.ins.activity.joinrecord.service.ActivityJoinRecordService;
import com.bz.ins.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 4:48 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service("activityJoinRecordDomain")
public class ActivityJoinRecordNativeDomain implements ActivityJoinRecordDomain{


    @Resource
    private ActivityJoinRecordService activityJoinRecordService;


    /**
     * 保存参与记录
     *
     * @param activityJoinRecordBo
     */
    @Override
    public void save(ActivityJoinRecordBo activityJoinRecordBo) throws ActivityException {
        activityJoinRecordService.save(BeanUtil.convert(activityJoinRecordBo, ActivityJoinRecord.class));
    }
}
