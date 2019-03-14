package com.btjf.insurance.acitivity.api.interceptor;


import com.btjf.insurance.acitivity.api.token.AccessTokenManage;
import com.btjf.insurance.user.bo.UserBo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component
public class RefreshDealerCacheInterceptor implements HandlerInterceptor {

    public static final String ACCESS_TOKEN = "AccessToken";
    public static final String MEMBER = "userBo";

    @Resource
    private AccessTokenManage accessTokenManage;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getHeader(ACCESS_TOKEN);
        UserBo member = this.accessTokenManage.get(accessToken);
        if(member != null){
            request.setAttribute(MEMBER, member);
        }
        return true;
    }
}
