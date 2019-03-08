package com.bz.ins.activity.season.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 黄雪冬
 * @date 2019/03/01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class ActivitySeasonBo implements Serializable {

    /**
     * 主键
     */
    private Integer ID;

    /**
     * 活动赛季期数
     */
    private Integer season;

    /**
     * 活动id
     */
    private Integer activityID;

    /**
     * 赛季开始时间
     */
    private Date startTime;

    /**
     * 赛季结束时间
     */
    private Date endTime;

    /**
     * 赛季状态（1:未开始；2:进行中;3:已结束）
     */
    private Integer status;

    /**
     * 是否需要初始化
     */
    private Boolean needInit;

    /**
     * 初始化时间
     */
    private Date initTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}