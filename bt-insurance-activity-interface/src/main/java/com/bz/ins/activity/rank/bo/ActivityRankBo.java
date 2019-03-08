package com.bz.ins.activity.rank.bo;

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
public class ActivityRankBo implements Serializable {

    /**
     * 主键
     */
    private Integer ID;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 用户id
     */
    private Integer userID;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 上次分数
     */
    private Integer lastScore;

    /**
     * 活动id
     */
    private Integer activityID;

    /**
     * 赛季id
     */
    private Integer seasonID;

    /**
     * 赛季
     */
    private Integer season;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date lastModifyTime;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 排名
     */
    private Integer rank;



    private static final long serialVersionUID = 1L;

    private ActivityRankBo(Builder builder) {
        setTotalScore(builder.totalScore);
        setUserID(builder.userID);
        setUserName(builder.userName);
        setLastScore(builder.lastScore);
        setActivityID(builder.activityID);
        setSeasonID(builder.seasonID);
        setSeason(builder.season);
        setCreateTime(builder.createTime);
        setLastModifyTime(builder.lastModifyTime);
        setIsDelete(builder.isDelete);
        setRank(builder.rank);
    }

    public static final class Builder {
        private Integer totalScore;
        private Integer userID;
        private String userName;
        private Integer lastScore;
        private Integer activityID;
        private Integer seasonID;
        private Integer season;
        private Date createTime;
        private Date lastModifyTime;
        private Boolean isDelete;
        private Integer rank;

        public Builder() {
        }

        public Builder totalScore(Integer val) {
            totalScore = val;
            return this;
        }

        public Builder userID(Integer val) {
            userID = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder lastScore(Integer val) {
            lastScore = val;
            return this;
        }

        public Builder activityID(Integer val) {
            activityID = val;
            return this;
        }

        public Builder seasonID(Integer val) {
            seasonID = val;
            return this;
        }

        public Builder season(Integer val) {
            season = val;
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

        public Builder rank(Integer val) {
            rank = val;
            return this;
        }

        public ActivityRankBo build() {
            return new ActivityRankBo(this);
        }
    }
}