package com.example.contabliumv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ContabliumV2Application {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ContabliumV2Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ContabliumV2Application.class, args);
    }

}
