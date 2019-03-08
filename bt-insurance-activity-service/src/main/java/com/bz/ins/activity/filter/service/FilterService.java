package com.bz.ins.activity.filter.service;

import com.bz.ins.activity.filter.mapper.ActivityFilterMapper;
import com.bz.ins.activity.filter.model.ActivityFilter;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 4:28 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class FilterService {

    @Resource
    private ActivityFilterMapper activityFilterMapper;



    public List<ActivityFilter> findByActivityIDAndSeasonID(Integer activityID, Integer seasonID) {
        return Lists.newArrayList();
    }


}
