package com.bz.ins.activity.answer.bo;

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
public class ActivityAnswerBo implements Serializable {

    /**
     * 主键
     */
    private Integer ID;

    /**
     * 问题ID
     */
    private Integer questionID;

    /**
     * 编码 ABCD
     */
    private String code;

    /**
     * 答案文案
     */
    private String content;

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
     * 分值
     */
    private Integer score;

    private static final long serialVersionUID = 1L;
}