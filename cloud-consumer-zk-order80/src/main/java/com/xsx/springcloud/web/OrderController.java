package com.xsx.springcloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @GetMapping(value = "/zk/some")
    public Object doSome(){
        return restTemplate.getForObject(INVOKE_URL + "/some", String.class);
    }
}
