package com.xsx.springcloud.service;

import com.xsx.springcloud.entities.Payment;

public interface PaymentService{
    int create(Payment payment);

    Payment getPaymentById(Long id);

    int createBySerial(String serial);
}
