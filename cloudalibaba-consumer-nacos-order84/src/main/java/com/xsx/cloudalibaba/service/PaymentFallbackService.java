package com.xsx.cloudalibaba.service;

import com.xsx.springcloud.entities.CommonResult;
import com.xsx.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    //service异常处理(降级)实现类
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回,没有该流水信息",new Payment(id, "errorSerial......"));
    }
}
