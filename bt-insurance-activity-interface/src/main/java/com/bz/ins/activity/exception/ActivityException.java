package com.bz.ins.activity.exception;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 7:09 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class ActivityException extends RuntimeException {

    public ActivityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivityException(String message) {
        super(message);
    }

    public ActivityException(Throwable cause) {
        super(cause);
    }
}
