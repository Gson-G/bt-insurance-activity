package com.bz.ins.activity.answer.model;

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
@TableName("t_Activity_Answer")
public class ActivityAnswer implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 问题ID
     */
    @TableField("FQuestionID")
    private Integer questionID;

    /**
     * 编码 ABCD
     */
    @TableField("FCode")
    private String code;

    /**
     * 答案文案
     */
    @TableField("FContent")
    private String content;

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