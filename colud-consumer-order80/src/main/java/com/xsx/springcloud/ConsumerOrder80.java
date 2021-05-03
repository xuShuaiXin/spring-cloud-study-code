package com.xsx.springcloud;

import com.xsx.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
/**
 *  @RibbonClient
 *      开启自定义提供者服务的负载规则
 *      属性:
 *          name = "需要替换负载规则的提供者服务名称"
 *          configuration = "自定义负载规则的类"
 */
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT", configuration = MySelfRule.class)
public class ConsumerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80.class, args);
    }
}
