package com.btjf.insurance.acitivity.api.interceptor;

import com.btjf.application.util.XaResult;
import com.btjf.common.utils.JSONUtils;
import com.btjf.insurance.acitivity.api.token.AccessTokenManage;
import com.btjf.insurance.user.bo.UserBo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 */
@Component
public class DealerOneLoginedInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(DealerOneLoginedInterceptor.class);

    public static final String ACCESS_TOKEN = "AccessToken";

    @Resource
    private AccessTokenManage accessTokenManage;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader(ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            convertResponse(response, HttpStatus.PAYMENT_REQUIRED, XaResult.unloginForNoAccessToken());
            return false;
        }
        UserBo userBo = this.accessTokenManage.get(accessToken);
        if (userBo == null) {
            convertResponse(response, HttpStatus.UNAUTHORIZED, XaResult.unlogin());
            return false;
        }
        return true;
    }

    private static void convertResponse(HttpServletResponse response, HttpStatus httpStatus, XaResult xaResult) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("text/xml;charset=UTF-8");
        response.getOutputStream().write(JSONUtils.toJSONByJackson(xaResult).getBytes("UTF-8"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
