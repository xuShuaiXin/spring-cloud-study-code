package com.xsx.springcloud.alibaba.service;

import io.seata.tm.api.GlobalTransactionContext;

import java.math.BigDecimal;

public interface AccountService {
    void decrease(Long userId, BigDecimal money);
    String xid = GlobalTransactionContext.getCurrentOrCreate().getXid();
}
