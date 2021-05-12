package com.xsx.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.xsx.springcloud.alibaba.daoconfig"})
public class MyBatisConfig {
}
