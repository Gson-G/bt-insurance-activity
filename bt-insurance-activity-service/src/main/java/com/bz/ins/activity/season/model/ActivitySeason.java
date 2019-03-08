package com.bz.ins.activity.season.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_Activity_Season")
public class ActivitySeason implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 活动赛季期数
     */
    @TableField("FSeason")
    private Integer season;

    /**
     * 活动id
     */
    @TableField("FActivityID")
    private Integer activityID;

    /**
     * 赛季开始时间
     */
    @TableField("FStartTime")
    private Date startTime;

    /**
     * 赛季结束时间
     */
    @TableField("FEndTime")
    private Date endTime;

    /**
     * 赛季状态（1:未开始；2:进行中;3:已结束）
     */
    @TableField("FStatus")
    private Integer status;

    /**
     * 是否需要初始化
     */
    @TableField("FNeedInit")
    private Boolean needInit;

    /**
     * 初始化时间
     */
    @TableField("FInitTime")
    private Date initTime;

    /**
     * 创建时间
     */
    @TableField("FCreateTime")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField("FLastModifyTime")
    private Date lastModifyTime;

    /**
     * 是否删除
     */
    @TableField("FIsDelete")
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}