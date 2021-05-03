package com.xsx.springcloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String INVOKE_URL = "http://cloud-provider-payment";
//    public static final String INVOKE_URL = "http://localhost:8006";

    @GetMapping(value = "/consumer/consul")
    public Object doSome(){
        return restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
    }
}
