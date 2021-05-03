package com.xsx.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

public interface PaymentService {
    //正常访问
    public String paymentInfo_OK(Integer id);
    //超时访问
    public String paymentInfo_TimeOut(Integer id);
    //服务熔断
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
