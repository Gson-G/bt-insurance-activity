package com.bz.ins.activity.prize.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("t_Activity_Prize")
public class ActivityPrize implements Serializable {

    @TableId(value = "FID", type = IdType.AUTO)
    private Integer ID;

    /**
     * 奖品名称
     */
    @TableField("FName")
    private String name;

    /**
     * 奖品类型（1:红包）
     */
    @TableField("FType")
    private Integer type;

    /**
     * 业务id
     */
    @TableField("FBusinessID")
    private Integer businessID;

    /**
     * 价值
     */
    @TableField("FAmount")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @TableField("FCreateTIme")
    private Date createTIme;

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