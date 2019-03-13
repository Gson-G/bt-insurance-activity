package com.bz.ins.activity.activity.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kantenmei
 * @date 2019/3/4
 * @time 10:23 AM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
public class ActivityParamBo<T extends Serializable> implements Serializable {


    private static final long serialVersionUID = 5036175682001507350L;

    private T object;

    private Integer userID;

    private String userName;

    private String activityStrategy;

    private Integer activityID;

    private Integer seasonID;

    private Integer season;

    private String prizeStrategy;

    private ActivityBo activityBo;

    private String activityCode;


    public ActivityParamBo() {
    }

    public ActivityParamBo(T object) {
        this.object = object;
    }

    public ActivityParamBo(T object, Integer activityID, Integer seasonID) {
        this.object = object;
        this.activityID = activityID;
        this.seasonID = seasonID;
    }

    private ActivityParamBo(Builder<T> builder) {
        setObject(builder.object);
        setUserID(builder.userID);
        setUserName(builder.userName);
        setActivityStrategy(builder.activityStrategy);
        setActivityID(builder.activityID);
        setSeasonID(builder.seasonID);
        setSeason(builder.season);
        setPrizeStrategy(builder.prizeStrategy);
        setActivityBo(builder.activityBo);
        setActivityCode(builder.activityCode);
    }


    public static final class Builder<T> {
        private T object;
        private Integer userID;
        private String userName;
        private String activityStrategy;
        private Integer activityID;
        private Integer seasonID;
        private Integer season;
        private String prizeStrategy;
        private ActivityBo activityBo;
        private String activityCode;

        public Builder() {
        }

        public Builder object(T val) {
            object = val;
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

        public Builder activityStrategy(String val) {
            activityStrategy = val;
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

        public Builder prizeStrategy(String val) {
            prizeStrategy = val;
            return this;
        }

        public Builder activityBo(ActivityBo val) {
            activityBo = val;
            return this;
        }

        public Builder activityCode(String val) {
            activityCode = val;
            return this;
        }

        public ActivityParamBo build() {
            return new ActivityParamBo(this);
        }
    }
}
