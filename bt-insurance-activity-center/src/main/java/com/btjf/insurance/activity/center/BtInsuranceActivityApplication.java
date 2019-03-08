package com.btjf.insurance.activity.center;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import com.bz.ins.activity.question.service.QuestionService;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.domain.ActivityRankDomain;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.bz.ins.activity.*.mapper")
@ComponentScan({"com.bz.ins.activity", "com.bz.ins.common.springboot.config"})
@EnableApolloConfig
public class BtInsuranceActivityApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BtInsuranceActivityApplication.class);
	}


	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(BtInsuranceActivityApplication.class, args);
		QuestionService questionService = applicationContext.getBean("questionService", QuestionService.class);
		List<QuestionAnswerPojo> answerPojos = questionService.getTestQuesttions(2);


		ActivityRankDomain activityRankDomain = applicationContext.getBean("activityRankDomain", ActivityRankDomain.class);
		System.out.println(answerPojos);
		List<ActivityRankBo> activityRankBos = activityRankDomain.getRankList(1,1);
		System.out.println(answerPojos);
	}

}
