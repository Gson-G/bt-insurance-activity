package com.btjf.insurance.acitivity.api;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kantenmei
 * @date 2019/2/28
 * @time 3:01 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@SpringBootApplication(exclude = {ActiveMQAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan({"com.btjf.insurance.acitivity"})

@EnableApolloConfig
public class InsuranceActivityApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InsuranceActivityApiApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(InsuranceActivityApiApplication.class, args);
    }
}
