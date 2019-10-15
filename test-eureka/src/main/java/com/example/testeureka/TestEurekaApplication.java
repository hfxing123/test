package com.example.testeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class TestEurekaApplication {

    @RequestMapping("/")
    private String home()
    {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(TestEurekaApplication.class, args);
    }

}
