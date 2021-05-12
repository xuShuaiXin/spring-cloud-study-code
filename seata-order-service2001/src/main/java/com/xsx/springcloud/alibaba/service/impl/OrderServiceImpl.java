package com.xsx.springcloud.alibaba.service.impl;

import com.xsx.springcloud.alibaba.dao.OrderDao;
import com.xsx.springcloud.alibaba.domain.Order;
import com.xsx.springcloud.alibaba.service.OrderAccountService;
import com.xsx.springcloud.alibaba.service.OrderService;
import com.xsx.springcloud.alibaba.service.OrderStorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderAccountService accountService;

    @Resource
    private OrderStorageService storageService;

    @Override
    //在方法上添加全局事务
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //1.创建订单
        log.info("正在创建订单=======");
        orderDao.create(order);
        //2.减少库存
        log.info("正在减少库存++++++++");
        storageService.decrease(order.getProductId(), order.getCount());
        //3.减少账户余额
        log.info("正在减少余额********");
        accountService.decrease(order.getUserId(), order.getMoney());
        //4.设置订单状态为已提交
        log.info("正在提交订单#############");
        // 传0才会变1 如果传其他值则不变
        orderDao.update(order.getUserId(), 0);
        log.info("订单提交成功!!!!!!!!!!!");
    }
    ///http://localhost:2001/order/create?userId=1002&productId=66&count=40&money=40
}
