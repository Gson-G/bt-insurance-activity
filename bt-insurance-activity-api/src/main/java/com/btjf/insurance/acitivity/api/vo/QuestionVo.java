package com.btjf.insurance.acitivity.api.vo;

import com.bz.ins.activity.question.bo.QuestionAnswerBo;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 8:38 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
public class QuestionVo {

    /**
     * 题目
     */
    private String questionTitle;

    /**
     * 题目id
     */
    private Integer questionID;

    /**
     *
     * 选项
     */
    private List<AnswerVo> options;

    public QuestionVo(QuestionAnswerBo questionAnswerBo) {
        this.questionTitle = questionAnswerBo.getContent();
        this.questionID = questionAnswerBo.getID();
        this.options = AnswerVo.convertToList(questionAnswerBo.getAnswers());

    }

    public static List<QuestionVo> convertToList(List<QuestionAnswerBo> boList) {
        List<QuestionVo> voList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(boList)) {
            return voList;
        }
        boList.forEach(t -> {
            QuestionVo questionVo = new QuestionVo(t);
            voList.add(questionVo);
        });
        return voList;
    }

}
