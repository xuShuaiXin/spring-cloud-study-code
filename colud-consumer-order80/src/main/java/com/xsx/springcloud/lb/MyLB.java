package com.xsx.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    //定义一个成员变量 atomicInteger
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获取请求访问次数
    public final int getAndIncrement(){
        int current;      //当前次数
        int next;         //当前次数+1后的正确次数值

        //使用自旋锁的方式获取计算访问次数(Ribbon底层也是这么实现的)
        do {
            current = atomicInteger.get();
            //请求访问次数超过10则从头开始 --2147483647为int类型参数最大值
            next = current >= 2147483647 ? 0:current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问，次数next: "+next);
        return next;
    }

    //负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标  ，
    // 每次服务重启动后rest接口计数从1开始。
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
