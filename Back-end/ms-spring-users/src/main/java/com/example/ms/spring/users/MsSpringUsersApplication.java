package com.example.ms.spring.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsSpringUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSpringUsersApplication.class, args);
    }

}
