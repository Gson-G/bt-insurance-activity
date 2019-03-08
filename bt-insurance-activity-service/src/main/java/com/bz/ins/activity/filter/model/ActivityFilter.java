package com.bz.ins.activity.filter.model;

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
 * @date 2019/03/05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_Activity_Filter")
public class ActivityFilter implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 名字
     */
    @TableField("FName")
    private String name;

    /**
     * 活动id
     */
    @TableField("FActivity")
    private Integer activity;

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
     * 类型(1:大于;2:小于;3:等于;4:大于等于;5:小于等于;6:不等于）
     */
    @TableField("FFilterType")
    private Integer filterType;

    /**
     * filterbean名称
     */
    @TableField("FFilterCode")
    private Integer filterCode;

    /**
     * 错误提示
     */
    @TableField("FErrorMessage")
    private String errorMessage;

    /**
     * 越小越在前面
     */
    @TableField("FOrder")
    private Integer order;

    /**
     * 最大值
     */
    @TableField("FMax")
    private Double max;

    /**
     * 最小值
     */
    @TableField("FMin")
    private Double min;

    /**
     * 等于值
     */
    @TableField("FEquals")
    private Double equals;

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