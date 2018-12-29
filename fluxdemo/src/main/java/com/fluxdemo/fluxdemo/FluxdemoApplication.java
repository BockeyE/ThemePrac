package com.fluxdemo.fluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class FluxdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluxdemoApplication.class, args);
    }

}

