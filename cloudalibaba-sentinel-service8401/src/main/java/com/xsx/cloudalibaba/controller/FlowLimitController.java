package com.xsx.cloudalibaba.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @Resource
    private RestTemplate restTemplate;
    public static final String PAYMENT_URL = "http://nacos-payment-provider";

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
//        Thread.sleep(500);
        log.info("AAAA");
        return "---------testA---------";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + " BBBB");
        return "---------testB---------";
    }
    @GetMapping("/payment/nacos/{id}")
    public String doSome(@PathVariable("id") Integer id){

        return restTemplate.getForObject(PAYMENT_URL + "/payment/nacos/" + id, String.class);
    }

    @GetMapping("/payment/nacos2/{id}")
    public String doSome2(@PathVariable("id") Integer id){

        return restTemplate.getForObject(PAYMENT_URL + "/payment/nacos/" + id, String.class);
    }

    @GetMapping("/testD")
    public String testD(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int a = 10/0;
        log.info(Thread.currentThread().getName() + " DDDD");
        return "---------testD---------";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")
    //blockHandler兜底方法只能处理sentinel控制台配置的异常,运行时异常不归他管
    //根据参数的索引位置来添加HotKey
    public String testHotKey( @RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
//        int a =1/0;     //会优先执行java的异常
        if (p1 == null){
            p1 = "空";
        }
        if (p2 == null){
            p2 = "空";
        }
        return "p1 == " + p1 + " p2 == " + p2;
    }
    //参数列表的位置必须与原方法一致,且最后一个参数一定要是 BlockException
    public String dealHandler_testHotKey(String p1, String p2, BlockException exception){
        return "访问失败 请稍后再试....";
    }
}
