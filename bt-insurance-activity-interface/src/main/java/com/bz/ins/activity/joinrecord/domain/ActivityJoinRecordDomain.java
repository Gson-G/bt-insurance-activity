package com.bz.ins.activity.joinrecord.domain;

import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.joinrecord.bo.ActivityJoinRecordBo;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 4:05 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public interface ActivityJoinRecordDomain {


    /**
     * 保存参与记录
     * @param activityJoinRecordBo
     */
    void save(ActivityJoinRecordBo activityJoinRecordBo) throws ActivityException;

}
