package com.btjf.insurance.acitivity.api.interceptor.config;

import com.btjf.insurance.acitivity.api.interceptor.DealerOneLoginedInterceptor;
import com.btjf.insurance.acitivity.api.interceptor.RefreshDealerCacheInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 */
@Configuration
public class WebConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private DealerOneLoginedInterceptor dealerOneLoginedInterceptor;
    @Autowired
    private RefreshDealerCacheInterceptor refreshDealerCacheInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则 //excludePathPatterns 用于排除拦截
        registry.addInterceptor(dealerOneLoginedInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/game/rank")
        ;

        registry.addInterceptor(refreshDealerCacheInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login")

        ;

    }
}