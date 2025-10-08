package com.example.todoListApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("----- Part01 todo api ----- ");
        System.out.println("----- Running Application... -----");
    }
}
