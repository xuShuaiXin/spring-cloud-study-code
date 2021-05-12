package com.xsx.springcloud.alibaba.controller;

import com.xsx.springcloud.alibaba.dao.OrderDao;
import com.xsx.springcloud.alibaba.domain.CommonResult;
import com.xsx.springcloud.alibaba.domain.Order;
import com.xsx.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderDao orderDao;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功!");
    }
}
