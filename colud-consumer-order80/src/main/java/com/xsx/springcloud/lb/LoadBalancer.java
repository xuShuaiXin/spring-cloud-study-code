package com.xsx.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    public ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
