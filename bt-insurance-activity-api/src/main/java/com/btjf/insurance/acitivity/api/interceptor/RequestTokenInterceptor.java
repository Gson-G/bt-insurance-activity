package com.btjf.insurance.acitivity.api.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @date 2018/11/27 0027
 * @time 下午 19:18
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class RequestTokenInterceptor implements HandlerInterceptor {

//    @Resource
//    private RequestTokenMapCache requestTokenMapCache;

    public RequestTokenInterceptor() {

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("requestToken");
//        String url = request.getRequestURI();
//        String url_token = url + "###" + token;
//        if (!StringUtils.isEmpty(token) && this.requestTokenMapCache.putRedis(url_token)) {
//            response.setContentType("text/xml;charset=UTF-8");
//            response.getOutputStream().write(JSONUtils.toJSONByJackson(XaResult.repeat()).getBytes("UTF-8"));
//            return false;
//        } else {
//            return true;
//        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        if (request.getHeader("requestToken") != null && response.getHeader("reCode") != null) {
//            this.requestTokenMapCache.delete(request.getRequestURI() + "###" + request.getHeader("requestToken"));
//        }

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
