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

		ApplicationContext applicationContext = SpringApplication.run(BtInsuranceActivityApplication.class, args);
		QuestionService questionService = applicationContext.getBean("questionService", QuestionService.class);
		List<QuestionAnswerPojo> answerPojos = questionService.getTestQuesttions(1,1,2);

		CommonRedisHelper commonRedisHelper = applicationContext.getBean("commonRedisHelper", CommonRedisHelper.class);



		ActivityRankDomain activityRankDomain = applicationContext.getBean("activityRankDomain", ActivityRankDomain.class);
		System.out.println(answerPojos);
		List<ActivityRankBo> activityRankBos = activityRankDomain.getRankList(1,1);
		System.out.println(answerPojos);

		ActivityQuestionNativeDomain activityQuestionDomain = applicationContext.getBean("activityQuestionDomain", ActivityQuestionNativeDomain.class);



//		//activityQuestionDomain.initAnswer();
//		CountDownLatch countDownLatch = new CountDownLatch(100);
//
//		for (int j  = 0; j< 100 ; j++) {
//			Thread thread = new Thread(new myRun(commonRedisHelper, countDownLatch));
//			thread.start();
//		}
//
//		countDownLatch.await();
//		System.out.println("i====" + i);

	}

//	static class myRun implements Runnable {
//
//		private CommonRedisHelper commonRedisHelper;
//
//		private CountDownLatch countDownLatch;
//
//		public myRun(CommonRedisHelper commonRedisHelper, CountDownLatch countDownLatch) {
//			this.commonRedisHelper = commonRedisHelper;
//			this.countDownLatch = countDownLatch;
//		}
//
//		@Override
//		public void run() {
//			RedisLockResult lock = commonRedisHelper.lock("hehe", 3000l);
//			try {
//				if (lock.isLockSuccess()) {
//					System.out.println(Thread.currentThread().getName() + "获取到锁");
//					Thread.currentThread().sleep(new Random().nextInt(100));
//					i++;
//				} else {
//					throw new ActivityException("网络繁忙请稍后重试");
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} finally {
//				if (lock.isLockSuccess()) {
//					System.out.println(Thread.currentThread().getName() + "释放到锁");
//					commonRedisHelper.releaseLock("hehe", lock);
//				}
//				countDownLatch.countDown();
//			}
//		}
//	}
}
