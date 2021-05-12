package com.xsx.springcloud.alibaba.service;

import com.xsx.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.OnOpen;
import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface OrderAccountService {
    //扣减用户余额
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
