package com.example.ms.spring.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsSpringEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSpringEurekaApplication.class, args);
    }

}
