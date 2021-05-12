package com.xsx.springcloud.alibaba;

import com.fasterxml.jackson.databind.util.ISO8601Utils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SeataStorageMainApp2002 {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SeataStorageMainApp2002.class, args);

    }
}
