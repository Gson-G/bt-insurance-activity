package com.btjf.insurance.acitivity.api.controller;

import com.btjf.application.components.controller.utils.ControllerLogUtils;
import com.btjf.application.util.XaResult;
import com.btjf.insurance.acitivity.api.interceptor.RefreshDealerCacheInterceptor;
import com.btjf.insurance.user.bo.UserBo;
import com.bz.ins.activity.exception.ActivityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 11:03 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class BaseController<T> {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    @ExceptionHandler({Exception.class})
    @ResponseBody
    public XaResult<T> handleUncaughtException(Exception exception) {
        ControllerLogUtils.printErrorLog(logger, exception);
        //this.deleteToken();
        return new XaResult("网络超时,请重试!");
    }

    @ExceptionHandler({ActivityException.class})
    @ResponseBody
    public XaResult<T> handleUncaughtActivityException(ActivityException exception) {
        ControllerLogUtils.printErrorLog(logger, exception);
        //this.deleteToken();
        return new XaResult(exception.getMessage());
    }

    /**
     * 获取当前登录的员工ID
     *
     * @return
     */
    public Integer getCurrentUserID() {
        UserBo userBo = getUser();
        return userBo != null ? userBo.getID() : null;
    }

    /**
     * 获取当前登录的角色ID
     *
     * @return
     */
    public UserBo getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return (UserBo) request.getAttribute(RefreshDealerCacheInterceptor.MEMBER);
    }


}
