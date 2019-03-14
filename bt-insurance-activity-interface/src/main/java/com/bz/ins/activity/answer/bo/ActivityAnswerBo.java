package com.bz.ins.activity.answer.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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

    private ActivityAnswerBo(Builder builder) {
        setQuestionID(builder.questionID);
        setCode(builder.code);
        setContent(builder.content);
        setCreateTime(builder.createTime);
        setLastModifyTime(builder.lastModifyTime);
        setIsDelete(builder.isDelete);
        setScore(builder.score);
    }


    public static final class Builder {
        private Integer questionID;
        private String code;
        private String content;
        private Date createTime;
        private Date lastModifyTime;
        private Boolean isDelete;
        private Integer score;

        public Builder() {
            super();
        }

        public Builder questionID(Integer val) {
            questionID = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder lastModifyTime(Date val) {
            lastModifyTime = val;
            return this;
        }

        public Builder isDelete(Boolean val) {
            isDelete = val;
            return this;
        }

        public Builder score(Integer val) {
            score = val;
            return this;
        }

        public ActivityAnswerBo build() {
            return new ActivityAnswerBo(this);
        }
    }
}