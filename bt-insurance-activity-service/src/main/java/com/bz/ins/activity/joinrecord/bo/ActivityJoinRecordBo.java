package com.bz.ins.activity.joinrecord.bo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @author 黄雪冬
 * @date 2019/03/07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActivityJoinRecordBo implements Serializable {

    private Integer ID;

    /**
     * 用户id
     */
    private Integer userID;

    /**
     * 活动id
     */
    private Integer activityID;

    /**
     * 赛季
     */
    private Integer season;

    /**
     * 赛季id
     */
    private Integer seasonID;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 奖品id
     */
    private Integer prizeID;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}