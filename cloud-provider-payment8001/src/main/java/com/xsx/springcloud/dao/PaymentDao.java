package com.xsx.springcloud.dao;

import com.xsx.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    int create(Payment payment);
    int createBySerial(String serial);
    Payment getPaymentById(Long id);
}
