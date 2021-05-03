package com.xsx.springcloud.Controller;
import com.xsx.springcloud.entities.CommonResult;
import com.xsx.springcloud.entities.Payment;
import com.xsx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value(value = "${server.port}")
    private String serverPort;

    //从容器获取服务发现接口 DiscoveryClient
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        System.out.println(payment);
        int result = paymentService.create(payment);
        log.info("插入结果+++++++" + result);
        if (result >= 1){
            return new CommonResult(200, "添加成功,端口号为: " + serverPort, result);
        }
        return new CommonResult(444, "添加失败");
    }

    @RequestMapping(value = "/payment/create/{serial}")
    public CommonResult create(@PathVariable("serial") String serial){
        System.out.println(serial);
        int result = paymentService.createBySerial(serial);
        log.info("插入结果+++++++" + result);
        if (result >= 1){
            return new CommonResult(200, "添加成功,端口号为: " + serverPort, result);
        }
        return new CommonResult(444, "添加失败");
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果+++++++" + payment);
        if (payment != null){
            return new CommonResult(200, "查询成功,端口号为: " + serverPort, payment);
        }
        return new CommonResult(444, "查询失败");
    }

    @GetMapping(value = "/payment/lb/discovery")
    public Object showDiscover(){
        //获取所有注册的 instanceId号
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("Service ==== " + service);
        }
        //获取 InstancesId为 CLOUD-PROVIDER-PAYMENT下的所有服务的信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getScheme() + "\t" +instance.getPort() + "\t" + instance.getHost() +
                    "\t" + instance.getScheme() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/timeout")
    public String timeOut(){
        try {
            //让程序睡眠3秒钟等同于 Thread.sleep(3000)
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
