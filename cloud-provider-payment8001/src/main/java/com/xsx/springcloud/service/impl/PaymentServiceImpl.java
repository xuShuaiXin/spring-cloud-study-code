package com.xsx.springcloud.service.impl;

import com.xsx.springcloud.dao.PaymentDao;
import com.xsx.springcloud.entities.Payment;
import com.xsx.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public int createBySerial(String serial) {

        return paymentDao.createBySerial(serial);
    }
}
