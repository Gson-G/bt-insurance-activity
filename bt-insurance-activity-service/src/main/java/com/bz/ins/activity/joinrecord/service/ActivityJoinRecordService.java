package com.bz.ins.activity.joinrecord.service;

import com.bz.ins.activity.joinrecord.mapper.ActivityJoinRecordMapper;
import com.bz.ins.activity.joinrecord.model.ActivityJoinRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 4:50 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class ActivityJoinRecordService {


    @Resource
    private ActivityJoinRecordMapper activityJoinRecordMapper;


    public void save(ActivityJoinRecord activityJoinRecord) {
        activityJoinRecordMapper.insert(activityJoinRecord);
    }
}
