package com.example.driver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DriverApplication {

    @Value("${server.port}")
    private String port;

    @GetMapping("/ride")
    public String processRide() {
        return "Ride processed by Driver Instance: " + port;
    }

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }
}
