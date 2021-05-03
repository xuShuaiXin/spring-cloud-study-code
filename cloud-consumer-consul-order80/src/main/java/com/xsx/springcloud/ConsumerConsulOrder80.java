package com.xsx.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsulOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerConsulOrder80.class, args);
    }
}
