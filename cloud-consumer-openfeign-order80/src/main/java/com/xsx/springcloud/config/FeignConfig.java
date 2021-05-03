package com.xsx.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     *  Feign日志级别:
     *   Logger.Level.NONE      不显示任何日志 (默认)
     *   Logger.Level.BASIC     仅记录请求方法,URL,响应码及执行时间
     *   Logger.Level.HEADERS   除了BASIC中定义的信息之外,还有请求和响应的头信息
     *   Lgger.Level.Full       除了HEADERS中定义的信息之外,还有请求头和响应正文等元素
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        // 请求和响应的头信息,请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
