package com.dowadream.customercare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCareApplication.class, args);
    }

}
