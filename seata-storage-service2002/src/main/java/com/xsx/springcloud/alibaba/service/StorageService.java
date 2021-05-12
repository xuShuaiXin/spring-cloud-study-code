package com.xsx.springcloud.alibaba.service;

import io.seata.tm.api.GlobalTransactionContext;

public interface StorageService {
    //扣减库存
    String xid = GlobalTransactionContext.getCurrentOrCreate().getXid();
    void decrease(Long productId, Integer count);
}
