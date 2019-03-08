package com.bz.ins.activity.prizepool.model;

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
 * @date 2019/03/01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_Activity_PrizePool")
public class ActivityPrizePool implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 活动id
     */
    @TableField("FActivityID")
    private Integer activityID;

    /**
     * 赛季
     */
    @TableField("FSeason")
    private Integer season;

    /**
     * 赛季id
     */
    @TableField("FSeasonID")
    private Integer seasonID;

    /**
     * 奖品id
     */
    @TableField("FPrizeID")
    private Integer prizeID;

    /**
     * 奖品数量 不填不限制
     */
    @TableField("FNumber")
    private Integer number;

    /**
     * 剩余数量
     */
    @TableField("FLastNum")
    private Integer lastNum;

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