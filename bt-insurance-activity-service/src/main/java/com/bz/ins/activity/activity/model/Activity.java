package com.bz.ins.activity.activity.model;

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
 * @date 2019/03/04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_Activity")
public class Activity implements Serializable {

    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 活动编码
     */
    @TableField("FCode")
    private String code;

    /**
     * 活动名称
     */
    @TableField("FName")
    private String name;

    /**
     * 开始时间
     */
    @TableField("FStartTime")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @TableField("FEndTime")
    private Date endTime;

    /**
     * 活动描述
     */
    @TableField("FDescription")
    private String description;

    /**
     * 活动状态（1:未开始；2:进行中；3:已关闭）
     */
    @TableField("FActivityStatus")
    private Integer activityStatus;

    /**
     * 活动策略名称
     */
    @TableField("FActivityStratey")
    private String activityStratey;

    /**
     * 发奖策略名称
     */
    @TableField("FPrizeStrategy")
    private String prizeStrategy;

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