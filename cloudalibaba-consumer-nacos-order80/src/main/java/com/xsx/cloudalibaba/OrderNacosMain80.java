package com.xsx.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  ////开启事务发现功能
public class OrderNacosMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain80.class, args);
    }
}
