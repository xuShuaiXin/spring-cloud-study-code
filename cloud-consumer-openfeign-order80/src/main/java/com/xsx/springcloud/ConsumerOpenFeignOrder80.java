package com.xsx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import sun.applet.Main;

@SpringBootApplication
@EnableFeignClients
public class ConsumerOpenFeignOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignOrder80.class, args);
    }
}
