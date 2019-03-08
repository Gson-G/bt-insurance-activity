package com.bz.ins.activity.prize.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author 黄雪冬
 * @date 2019/03/01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActivityPrizeBo implements Serializable {

    private Integer ID;

    /**
     * 奖品名称
     */
    private String name;

    /**
     * 奖品类型（1:红包）
     */
    private Integer type;

    /**
     * 业务id
     */
    private Integer businessID;

    /**
     * 价值
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createTIme;

    /**
     * 修改时间
     */
    private Date lastModifyTime;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}