package com.example.ms.spring.users.util;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class ConfigR2DBC {

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, "postgres")
                        .option(PASSWORD, "1234")
                        .option(DATABASE, "ms-user-db")
                        .build());
    }
}
