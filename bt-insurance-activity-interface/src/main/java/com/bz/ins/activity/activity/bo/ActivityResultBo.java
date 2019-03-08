package com.bz.ins.activity.activity.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 1:38 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
public class ActivityResultBo<T> implements Serializable {
    private static final long serialVersionUID = -4169893416598642319L;

    private static final Integer SUCCESS_CODE = 1;

    private static final Integer FAIL_CODE = 0;

    private Integer code;

    private T object;


    public ActivityResultBo() {
    }

    public ActivityResultBo(Integer code, T t) {
        this.code = code;
        this.object = t;
    }

    public ActivityResultBo(Integer code) {
        this.code = code;
    }

    public ActivityResultBo(T t) {
        this.object = t;
    }

    public static ActivityResultBo fail() {
        return new ActivityResultBo(FAIL_CODE);
    }

    public static boolean isSuccess(ActivityResultBo activityResultBo) {
        return null != activityResultBo && SUCCESS_CODE.equals(activityResultBo.getCode());
    }

    public static <T> ActivityResultBo<T> success(T t) {
        return new ActivityResultBo(SUCCESS_CODE, t);
    }
    public static ActivityResultBo success() {
        return new ActivityResultBo(SUCCESS_CODE);
    }
}
