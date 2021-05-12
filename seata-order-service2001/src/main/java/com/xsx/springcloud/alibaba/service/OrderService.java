package com.xsx.springcloud.alibaba.service;


import com.xsx.springcloud.alibaba.domain.Order;
import io.seata.tm.api.GlobalTransactionContext;


public interface OrderService {
    String xid = GlobalTransactionContext.getCurrentOrCreate().getXid();
    //创建订单
    void create(Order order);
}
