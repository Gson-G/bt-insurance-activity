package com.bz.ins.activity.joinrecord.model;

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
@TableName("t_Activity_JoinRecord")
public class ActivityJoinRecord implements Serializable {

    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 用户id
     */
    @TableField("FUserID")
    private Integer userID;

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
     * 得分
     */
    @TableField("FScore")
    private Integer score;

    /**
     * 奖品id
     */
    @TableField("FPrizeID")
    private Integer prizeID;

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

    @TableField("FIsDelete")
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}