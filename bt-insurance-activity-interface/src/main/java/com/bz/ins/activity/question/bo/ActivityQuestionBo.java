package com.bz.ins.activity.question.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class ActivityQuestionBo implements Serializable {

    /**
     * 主键
     */
    private Integer ID;

    /**
     * 正确答案id
     */
    private Integer answerID;

    /**
     * 题目文案
     */
    private String content;

    /**
     * 活动id
     */
    private Integer activityID;

    /**
     * 赛季
     */
    private Integer season;

    /**
     * 赛季id
     */
    private Integer seasonID;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 权重
     */
    private Integer weight;

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