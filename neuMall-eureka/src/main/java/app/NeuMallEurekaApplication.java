package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName: TestEurekaApplication
 * @Description:
 * @Author: shengtt
 * @Date: 2019/4/1
 */
@SpringBootApplication
@EnableEurekaServer
public class NeuMallEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeuMallEurekaApplication.class, args);
    }

}