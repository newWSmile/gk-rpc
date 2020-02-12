package com.smile.gkrpc.server;

import com.smile.gkrpc.Request;
import com.smile.gkrpc.ServiceDescriptor;
import com.smile.gkrpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理暴漏方法
 */
@Slf4j
public class ServiceManager {

    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method publicMethod : publicMethods) {
            ServiceInstance instance = new ServiceInstance(bean,publicMethod);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, publicMethod);
            services.put(serviceDescriptor,instance);
            log.info("regiter service: {} {} ",serviceDescriptor.getClazz(),serviceDescriptor.getMethod());
        }

    }

    public ServiceInstance lookUp(Request request){
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        return services.get(serviceDescriptor);
    }


}
