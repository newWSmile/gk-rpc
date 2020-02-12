package com.smile.gkrpc;

import lombok.Data;

import java.util.Objects;

/**
 * rpc的一个请求
 */
@Data
public class Request {

    private ServiceDescriptor serviceDescriptor;

    private Object[] parameters;


}
