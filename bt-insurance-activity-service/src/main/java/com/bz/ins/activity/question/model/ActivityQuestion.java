package com.bz.ins.activity.question.model;

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
@TableName("t_Activity_Question")
public class ActivityQuestion implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 正确答案id
     */
    @TableField("FAnswerID")
    private Integer answerID;

    /**
     * 题目文案
     */
    @TableField("FContent")
    private String content;

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
     * 分数
     */
    @TableField("FScore")
    private Integer score;

    /**
     * 权重
     */
    @TableField("FWeight")
    private Integer weight;

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