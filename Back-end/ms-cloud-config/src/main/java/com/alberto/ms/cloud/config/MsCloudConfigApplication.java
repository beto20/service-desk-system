package com.alberto.ms.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCloudConfigApplication.class, args);
    }

}
