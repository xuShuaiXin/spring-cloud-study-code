package com.xsx.springcloud.controller;

import com.xsx.springcloud.entities.CommonResult;
import com.xsx.springcloud.entities.Payment;
import com.xsx.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/consumer", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class OrderController {
    //订单服务不应该写死(集群情况下)
//    public static final String PAYMENT_URL="http://localhost:8002";

    /**
     *  集群情况下如何设置访问的 url?
     *     1. 应该直接请求提供者服务的application的name值 无需关心端口号和url
     *          多个提供者集群共用一个 application的name值 cloud-provider-payment调用的时候是全大写
     *     2. 需要开启负载均衡 @LoadBalanced 在RestTemplate @Bean注解声明上
     */

    // PAYMENT_APN --> payment_ApplicationName
    public static final String PAYMENT_APN = "http://CLOUD-PROVIDER-PAYMENT";
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        log.info("进入了consumer的create处理器方法");
        return restTemplate.postForObject(PAYMENT_APN + "/payment/create",payment,CommonResult.class);
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayment(@PathVariable(value = "id") Long id){
        log.info("进入了consumer的getPayment处理器方法");
        return restTemplate.getForObject(PAYMENT_APN + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/payment/getForEntity/{id}")
    public CommonResult getForEntityPayment(@PathVariable(value = "id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_APN + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult(444, "查询错误");
        }
    }

    //测试自定义负载均衡算法的请求映射
    @GetMapping(value = "/payment/lb/get/{id}")
    public CommonResult getPaymentLB(@PathVariable(value = "id") Long id){

        //1.获取 getInstances所有服务的list集合 注: discoveryClient需要使用spring自动注入引入到该类中
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        if (instances == null || instances.size() == 0){
            return new CommonResult(444, "无可用的服务提供者端口");
        }
        //2.通过自定义的负载均衡算法 获取到真正需要提供服务的服务器
        ServiceInstance instance = loadBalancer.instance(instances);
        //3.获取提供服务的uri
        URI uri = instance.getUri();
        System.out.println(uri);
        //4.然后使用RestTemplate 拼接url和参数 调用
        return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
    }
}
