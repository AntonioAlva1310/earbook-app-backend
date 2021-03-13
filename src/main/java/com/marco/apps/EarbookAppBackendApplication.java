package com.marco.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EarbookAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EarbookAppBackendApplication.class, args);
    }





}