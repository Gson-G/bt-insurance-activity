package com.bz.ins.activity.filter;

import com.bz.ins.activity.filter.domain.ActivityFilterDomain;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 2:52 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Service
public class FilterHolder {


    @Resource
    private ActivityFilterDomain activityFilterDomain;


    public static List<ActivityRoleFilter> getRoleFilterList() {
        return Lists.newArrayList();
    }

}
