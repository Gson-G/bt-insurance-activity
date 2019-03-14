package com.bz.ins.activity.question.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 4:38 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreResult implements Serializable {

    private static final long serialVersionUID = 4131529822801148053L;
    /**
     * 得分
     */
    private Integer score;

    /**
     * 正确题目答案id
     */
    private Integer rightAnswerID;

    private Boolean right;


    private ScoreResult(Builder builder) {
        setScore(builder.score);
        setRightAnswerID(builder.rightAnswerID);
        setRight(builder.right);
    }


    public static final class Builder {
        private Integer score;
        private Integer rightAnswerID;
        private Boolean right;

        public Builder() {
            super();
        }

        public Builder score(Integer val) {
            score = val;
            return this;
        }

        public Builder rightAnswerID(Integer val) {
            rightAnswerID = val;
            return this;
        }

        public Builder right(Boolean val) {
            right = val;
            return this;
        }

        public ScoreResult build() {
            return new ScoreResult(this);
        }
    }
}
