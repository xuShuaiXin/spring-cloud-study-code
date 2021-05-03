package com.xsx.springcloud.web;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value(value = "${server.port}")
    private String port;

    @GetMapping(value = "/payment/consul")
    public String payment(){
        return "使用 Consul 注册的应用启动了, 端口号: " + port + "\t" + UUID.randomUUID().toString();
    } 
}
