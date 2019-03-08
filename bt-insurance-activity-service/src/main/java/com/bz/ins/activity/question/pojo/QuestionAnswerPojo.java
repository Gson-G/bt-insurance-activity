package com.bz.ins.activity.question.pojo;

import com.bz.ins.activity.answer.model.ActivityAnswer;
import com.bz.ins.activity.question.model.ActivityQuestion;
import lombok.Data;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/5
 * @time 2:15 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
public class QuestionAnswerPojo extends ActivityQuestion {


    private List<ActivityAnswer> answers;

    private ActivityAnswer rightAnswer;

}
