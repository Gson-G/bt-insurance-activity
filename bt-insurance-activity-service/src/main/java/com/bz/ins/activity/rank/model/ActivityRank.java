package com.bz.ins.activity.rank.model;

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
 * @date 2019/03/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_Activity_Rank")
public class ActivityRank implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 总分
     */
    @TableField("FTotalScore")
    private Integer totalScore;

    /**
     * 用户id
     */
    @TableField("FUserID")
    private Integer userID;

    /**
     * 用户名
     */
    @TableField("FUserName")
    private String userName;

    /**
     * 上次分数
     */
    @TableField("FLastScore")
    private Integer lastScore;

    /**
     * 活动id
     */
    @TableField("FActivityID")
    private Integer activityID;

    /**
     * 赛季id
     */
    @TableField("FSeasonID")
    private Integer seasonID;

    /**
     * 赛季
     */
    @TableField("FSeason")
    private Integer season;

    /**
     * 最大的答题号
     */
    @TableField("FMaxQuestionCode")
    private Integer maxQuestionCode;

    /**
     * 创建时间
     */
    @TableField("FCreateTime")
    private Date createTime;

    /**
     * 修改时间
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