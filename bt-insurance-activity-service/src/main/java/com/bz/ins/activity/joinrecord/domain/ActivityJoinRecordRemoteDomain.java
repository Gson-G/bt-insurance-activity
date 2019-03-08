package com.bz.ins.activity.joinrecord.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.joinrecord.bo.ActivityJoinRecordBo;
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
public class ActivityJoinRecordRemoteDomain implements ActivityJoinRecordDomain{


    @Resource
    private ActivityJoinRecordDomain activityJoinRecordDomain;

    /**
     * 保存参与记录
     *
     * @param activityJoinRecordBo
     */
    @Override
    public void save(ActivityJoinRecordBo activityJoinRecordBo) throws ActivityException {
        activityJoinRecordDomain.save(activityJoinRecordBo);
    }
}
