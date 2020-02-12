package com.smile.gkrpc.server;

import com.smile.gkrpc.Request;
import com.smile.gkrpc.common.utils.ReflectionUtils;

public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());

    }

}
