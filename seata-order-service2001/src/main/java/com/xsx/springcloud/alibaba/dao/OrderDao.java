package com.xsx.springcloud.alibaba.dao;

import com.xsx.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //创建订单
    void create(Order order);

    //修改订单状态 0未付款 1已付款
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
