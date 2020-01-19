package com.neusoft.mall;
import com.neusoft.mall.util.ConversationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
/**
 * @ClassName: NeuMallApplication
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@EnableEurekaClient
@MapperScan(value ="com.**.dao")
@SpringBootApplication
public class NeuMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeuMallApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ConversationFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}