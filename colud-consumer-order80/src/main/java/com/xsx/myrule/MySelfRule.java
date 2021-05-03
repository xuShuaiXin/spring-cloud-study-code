package com.xsx.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 自定义负载均衡路由规则类
 *    默认算法为:
 *      com.netflix.loadbalancer.RoundRobinRule
 *          默认为轮询
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //重新定义为算法规则为 随机
        return new RandomRule();
    }
}
