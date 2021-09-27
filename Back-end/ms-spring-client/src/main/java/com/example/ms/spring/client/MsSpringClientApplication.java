package com.example.ms.spring.client;

import com.example.ms.spring.client.model.Client;
import com.example.ms.spring.client.model.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MsSpringClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSpringClientApplication.class, args);
    }

}

