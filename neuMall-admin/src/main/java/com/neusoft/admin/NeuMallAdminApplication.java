package com.neusoft.admin;

import com.neusoft.admin.util.ConversationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;


/**
 * @ClassName: TestEurekaApplication
 * @Description:
 * @Author: shengtt
 * @Date: 2019/4/1
 */
@SpringBootApplication
@EnableEurekaServer
@MapperScan(value ="com.**.dao")
public class NeuMallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeuMallAdminApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ConversationFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}