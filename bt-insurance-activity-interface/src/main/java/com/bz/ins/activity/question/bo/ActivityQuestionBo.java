package com.bz.ins.activity.question.bo;

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

    private ActivityQuestionBo(Builder builder) {
        setAnswerID(builder.answerID);
        setContent(builder.content);
        setActivityID(builder.activityID);
        setSeason(builder.season);
        setSeasonID(builder.seasonID);
        setScore(builder.score);
        setWeight(builder.weight);
        setCreateTime(builder.createTime);
        setLastModifyTime(builder.lastModifyTime);
        setIsDelete(builder.isDelete);
    }


    public static final class Builder {
        private Integer answerID;
        private String content;
        private Integer activityID;
        private Integer season;
        private Integer seasonID;
        private Integer score;
        private Integer weight;
        private Date createTime;
        private Date lastModifyTime;
        private Boolean isDelete;

        public Builder() {
        }

        public Builder answerID(Integer val) {
            answerID = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder activityID(Integer val) {
            activityID = val;
            return this;
        }

        public Builder season(Integer val) {
            season = val;
            return this;
        }

        public Builder seasonID(Integer val) {
            seasonID = val;
            return this;
        }

        public Builder score(Integer val) {
            score = val;
            return this;
        }

        public Builder weight(Integer val) {
            weight = val;
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

        public ActivityQuestionBo build() {
            return new ActivityQuestionBo(this);
        }
    }
}