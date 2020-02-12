package com.smile.gkrpc.server;

import com.smile.gkrpc.Request;
import com.smile.gkrpc.ServiceDescriptor;
import com.smile.gkrpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    ServiceManager serviceManager;

    @Before
    public void init(){
        serviceManager = new ServiceManager();
        register();
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class,bean);

    }

    @Test
    public void lookUp() {

        Request request = new Request();
        Method hello = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class,hello);

        request.setServiceDescriptor(serviceDescriptor);
        ServiceInstance serviceInstance = serviceManager.lookUp(request);
        assertNotNull(serviceInstance);


    }
}