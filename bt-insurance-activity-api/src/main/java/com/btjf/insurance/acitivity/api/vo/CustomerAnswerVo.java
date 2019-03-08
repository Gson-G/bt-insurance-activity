package com.btjf.insurance.acitivity.api.vo;

import com.bz.ins.activity.question.bo.QuestionScoreBo;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 10:48 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAnswerVo {

    private Integer questionID;

    private Integer optionID;

    private Integer costTime;


    public static List<QuestionScoreBo> converToList(List<CustomerAnswerVo> voList) {
        List<QuestionScoreBo> resultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(voList)) {
            return resultList;
        }
        voList.forEach(t -> resultList.add(converToQuestionScoreBo(t)));
        return resultList;
    }

    public static QuestionScoreBo converToQuestionScoreBo(CustomerAnswerVo customerAnswerVo) {
        QuestionScoreBo questionScoreBo = new QuestionScoreBo();
        questionScoreBo.setCost(customerAnswerVo.getCostTime());
        questionScoreBo.setQuestionID(customerAnswerVo.getQuestionID());
        questionScoreBo.setAnswerID(customerAnswerVo.getOptionID());
        return questionScoreBo;
    }

}
