package com.xsx.springcloud.controller;

import com.xsx.springcloud.entities.CommonResult;
import com.xsx.springcloud.service.PaymentOpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/consumer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class OrderController {
    @Resource
    private PaymentOpenFeignService paymentOpenFeignService;

    //openFeign自带负载均衡 默认使用轮询算法
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayment(@PathVariable(value = "id") Long id){
        log.info("进入了consumer的getPayment处理器方法, 使用OpenFeign...");
        return paymentOpenFeignService.getPaymentById(id);
    }

    /**
     *  OpenFeign的客户端默认等待时间是 1秒钟
     *      超时会报错 Read timed out
     *      可以在yml设置超时时间
     *      因为OpenFeign默认继承了 Ribbon,负载均衡底层使用的也是Ribbon
     *      所以要在yml中开启设置并设置 Ribbon超时控制
     */

    @GetMapping(value = "/payment/timeout")
    public String timeOut(){
        log.info("进入了consumer的timeOut处理器方法, 使用OpenFeign...");
        return paymentOpenFeignService.timeOut();
    }
}
