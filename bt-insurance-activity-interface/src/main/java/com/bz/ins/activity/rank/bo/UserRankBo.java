package com.bz.ins.activity.rank.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kantenmei
 * @date 2019/3/7
 * @time 2:32 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRankBo implements Serializable {

    private Integer rank;

    private Integer totolScore;

}
