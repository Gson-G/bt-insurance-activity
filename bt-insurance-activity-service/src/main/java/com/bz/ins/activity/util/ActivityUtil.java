package com.bz.ins.activity.util;

import com.bz.ins.activity.activity.strategy.ActivityStrategy;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 11:23 AM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ActivityUtil {



    public static ActivityStrategy getStrategy(String strategyName) {

        return SpringBeanUtil.getBean(strategyName, ActivityStrategy.class);
        //Set<Class<?>> classSet = new Reflections("org.cboard").getTypesAnnotatedWith(ProviderName.class);

    }



}
