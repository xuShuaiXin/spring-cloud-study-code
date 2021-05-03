package com.xsx.springcloud.service;

import org.springframework.stereotype.Component;

@Component  //记得要添加到容器中
public class PaymentFallbackService implements PaymentHystrixService {
    //统一处理 服务降级,客户端去调用服务端,碰上服务端宕机或关闭
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
