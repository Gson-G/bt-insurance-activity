package com.bz.ins.activity.activity.bo;

import com.bz.ins.activity.filter.bo.ActivityFilterBo;
import com.bz.ins.activity.season.bo.ActivitySeasonBo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author gtm
 * @date 2019/03/04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActivityBo implements Serializable {

    private Integer ID;

    /**
     * 活动编码
     */
    private String code;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动状态（1:未开始；2:进行中；3:已关闭）
     */
    private Integer activityStatus;

    /**
     * 活动策略名称
     */
    private String activityStratey;

    /**
     * 发奖策略名称
     */
    private String prizeStrategy;

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

    /**
     * 赛季id
     */
    private Integer seasonID;

    /**
     * 赛季信息
     */
    private ActivitySeasonBo activitySeasonBo;

    /**
     * 限制条件
     */
    private List<ActivityFilterBo> filterBoList;

    private static final long serialVersionUID = 1L;
}