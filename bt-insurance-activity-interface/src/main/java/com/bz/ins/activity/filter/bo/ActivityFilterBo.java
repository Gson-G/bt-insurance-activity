package com.bz.ins.activity.filter.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 黄雪冬
 * @date 2019/03/05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActivityFilterBo implements Serializable {

    /**
     * 主键
     */
    private Integer ID;

    /**
     * 名字
     */
    private String name;

    /**
     * 活动id
     */
    private Integer activity;

    /**
     * 赛季id
     */
    private Integer seasonID;

    /**
     * 赛季
     */
    private Integer season;

    /**
     * 类型(1:大于;2:小于;3:等于;4:大于等于;5:小于等于;6:不等于）
     */
    private Integer filterType;

    /**
     * filterbean名称
     */
    private Integer filterCode;

    /**
     * 错误提示
     */
    private String errorMessage;

    /**
     * 越小越在前面
     */
    private Integer order;

    /**
     * 最大值
     */
    private Double max;

    /**
     * 最小值
     */
    private Double min;

    /**
     * 等于值
     */
    private Double equals;

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