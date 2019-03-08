package com.bz.ins.activity.question.bo;

import com.bz.ins.activity.answer.bo.ActivityAnswerBo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 3:49 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
public class QuestionAnswerBo extends ActivityQuestionBo{

    private static final long serialVersionUID = -6833236109790747918L;
    /**
     * 所有选项集合
     */
    private List<ActivityAnswerBo> answers;


    private ActivityAnswerBo rightAnswer;

}
