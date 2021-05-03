package com.xsx.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("time:"+new Date()+"\t 执行了自定义的全局过滤器: "+"MyLogGateWayFilter"+"hello");
        //获取参数
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if (name == null){
            log.info("*******用户名为null 无法登陆!!!");
            //返回一个 406错误代码, "Not Acceptable" --> 不可接受
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();    //不放行
        }
        log.info("*******用户名:" + name + " 登陆成功!!!");
        return chain.filter(exchange);      //表示放行
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
