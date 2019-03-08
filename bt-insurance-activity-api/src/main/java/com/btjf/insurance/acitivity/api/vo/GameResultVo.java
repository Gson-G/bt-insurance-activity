package com.btjf.insurance.acitivity.api.vo;

import com.bz.ins.activity.question.bo.ScoreResult;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 4:30 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
public class GameResultVo {

    private Integer score;

    private Integer rightID;


    public GameResultVo(ScoreResult scoreResult) {
        this.score = scoreResult.getScore();
        this.rightID = scoreResult.getRightAnswerID();
    }
}
