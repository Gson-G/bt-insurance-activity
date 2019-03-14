package com.btjf.insurance.activity.center;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.bz.ins.activity.exception.ActivityException;
import com.bz.ins.activity.question.domain.ActivityQuestionNativeDomain;
import com.bz.ins.activity.question.pojo.QuestionAnswerPojo;
import com.bz.ins.activity.question.service.QuestionService;
import com.bz.ins.activity.rank.bo.ActivityRankBo;
import com.bz.ins.activity.rank.domain.ActivityRankDomain;
import com.bz.ins.activity.util.CommonRedisHelper;
import com.bz.ins.activity.util.ExcelPoji;
import com.bz.ins.activity.util.ExcelUtil;
import com.bz.ins.activity.util.RedisLockResult;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.bz.ins.activity.*.mapper")
@ComponentScan({"com.bz.ins.activity", "com.bz.ins.common.springboot.config"})
@EnableApolloConfig
public class BtInsuranceActivityApplication extends SpringBootServletInitializer {

	private static Integer i = 0;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BtInsuranceActivityApplication.class);
	}


	public static void main(String[] args) throws Exception {
		SpringApplication.run(BtInsuranceActivityApplication.class, args);
	}

}
