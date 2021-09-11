package com.example.ms.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsSpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSpringCloudGatewayApplication.class, args);
    }

}
