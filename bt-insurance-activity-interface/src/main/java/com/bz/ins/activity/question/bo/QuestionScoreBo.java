package com.bz.ins.activity.question.bo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 4:36 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
public class QuestionScoreBo implements Serializable {

    private static final long serialVersionUID = 2239262620364987717L;
    private Integer cost;

    private Integer questionID;

    private Integer answerID;
}
