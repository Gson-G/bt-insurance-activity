package com.bz.ins.activity.question.mapper;

import com.bz.ins.activity.question.model.ActivityQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityQuestionMapper extends BaseMapper<ActivityQuestion> {



    List<QuestionAnswerPojo> findQuestionForGame(@Param("idList") List<Integer> idList);


    List<QuestionAnswerPojo> findQuestionForGameIds(@Param("num") Integer num);


    List<QuestionAnswerPojo> findAll(@Param("questionID") Integer num);

}