package com.xsx.springcloud.alibaba.service;

import com.xsx.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface OrderStorageService {
    //扣减库存
    @PostMapping(value = "/storage/decrease")
    //使用的是post方式
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
