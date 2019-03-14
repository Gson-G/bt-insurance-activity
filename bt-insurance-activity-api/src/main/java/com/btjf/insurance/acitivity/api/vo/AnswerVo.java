package com.btjf.insurance.acitivity.api.vo;

import com.bz.ins.activity.answer.bo.ActivityAnswerBo;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author kantenmei
 * @date 2019/3/6
 * @time 8:39 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Data
@NoArgsConstructor
public class AnswerVo {


    private Integer optionID;

    private String optionContent;

    public AnswerVo(ActivityAnswerBo activityAnswerBo) {
        this.optionID = activityAnswerBo.getID();
        this.optionContent = activityAnswerBo.getContent();
    }


    public static List<AnswerVo> convertToList(List<ActivityAnswerBo> boList) {
        List<AnswerVo> voList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(boList)) {
            return voList;
        }
        boList.forEach(t -> {
            AnswerVo answerVo = new AnswerVo(t);
            voList.add(answerVo);
        });
        return voList;
    }



}
