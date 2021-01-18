package com.mfcustomerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MfCustomerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MfCustomerApiApplication.class, args);
    }
}
