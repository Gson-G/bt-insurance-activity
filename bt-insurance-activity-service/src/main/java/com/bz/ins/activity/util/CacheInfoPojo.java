package com.bz.ins.activity.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 5:17 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheInfoPojo<T> implements Serializable {

    private Long expTime;

    private T object;



}
