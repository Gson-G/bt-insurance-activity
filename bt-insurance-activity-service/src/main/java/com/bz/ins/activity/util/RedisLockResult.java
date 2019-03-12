package com.bz.ins.activity.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kantenmei
 * @date 2019/3/12
 * @time 11:02 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisLockResult {

    private String lockedKey;

    private boolean lockSuccess;


}
