package com.xsx.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {
    @Value(value = "${server.port}")
    private String port;

    @GetMapping(value = "/some")
    public String doSome(){
        return "使用 ZooKeeper 注册的应用启动了, 端口号: " + port + "\t" + UUID.randomUUID().toString();
    }
}
