package com.bz.ins.activity.joinrecord.bo;

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
public class ActivityJoinRecordBo implements Serializable {

    private Integer ID;

    /**
     * 用户id
     */
    private Integer userID;

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
     * 得分
     */
    private Integer score;

    /**
     * 奖品id
     */
    private Integer prizeID;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    private ActivityJoinRecordBo(Builder builder) {
        setUserID(builder.userID);
        setActivityID(builder.activityID);
        setSeason(builder.season);
        setSeasonID(builder.seasonID);
        setScore(builder.score);
        setPrizeID(builder.prizeID);
        setCreateTime(builder.createTime);
        setLastModifyTime(builder.lastModifyTime);
        setIsDelete(builder.isDelete);
    }


    public static final class Builder {
        private Integer userID;
        private Integer activityID;
        private Integer season;
        private Integer seasonID;
        private Integer score;
        private Integer prizeID;
        private Date createTime;
        private Date lastModifyTime;
        private Boolean isDelete;

        public Builder() {
        }

        public Builder userID(Integer val) {
            userID = val;
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

        public Builder prizeID(Integer val) {
            prizeID = val;
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

        public ActivityJoinRecordBo build() {
            return new ActivityJoinRecordBo(this);
        }
    }
}