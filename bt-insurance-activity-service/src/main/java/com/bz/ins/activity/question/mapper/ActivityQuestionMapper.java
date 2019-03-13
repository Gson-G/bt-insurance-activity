package com.bz.ins.activity.question.mapper;

import com.bz.ins.activity.question.model.ActivityQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityQuestionMapper extends BaseMapper<ActivityQuestion> {


    List<QuestionAnswerPojo> findQuestionForGame(@Param("idList") List<Integer> idList);


    List<QuestionAnswerPojo> findQuestionForGameIds(@Param("activityID") Integer activity, @Param("seasonID") Integer seasonID, @Param("num") Integer num);

    List<QuestionAnswerPojo> findAll(@Param("questionID") Integer num);

    void updateRightAnswer(@Param("answerID") Integer answerID, @Param("id") Integer id);

    List<QuestionAnswerPojo> findQuestionForTaxGameIds(@Param("activityID")Integer activityID, @Param("seasonID")Integer seasonID,
                                                       @Param("maxQuestion")Integer maxQuestion, @Param("number")Integer number);

}