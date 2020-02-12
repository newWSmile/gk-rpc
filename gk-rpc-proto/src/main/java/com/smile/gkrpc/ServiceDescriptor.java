package com.smile.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    private String clazz;

    private String method;

    private String returnType;

    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());
        Class<?>[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        serviceDescriptor.setParameterTypes(parameterTypes);
        return serviceDescriptor;

    }


    @Override
    public boolean equals(Object o) {
      if (this ==  o ) {
          return true;
      }
      if (o == null || getClass()!=o.getClass() ){
          return false;
      }
      ServiceDescriptor other = (ServiceDescriptor) o;
      return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
       return toString().hashCode();
    }

    @Override
    public String toString() {
        return "clazz="+clazz
                +",method="+method
                +",returnTYpe="+ returnType
                +"parameterTypes="+Arrays.toString(parameterTypes);
    }
}
