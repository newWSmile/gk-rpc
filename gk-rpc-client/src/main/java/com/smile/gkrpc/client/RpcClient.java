package com.smile.gkrpc.client;

import com.smile.gkrpc.codec.Decoder;
import com.smile.gkrpc.codec.Encoder;
import com.smile.gkrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @ClassName RpcClient
 * @Description TODO
 * @Author Smile
 * Data 2020/2/12 17:24
 **/
public class RpcClient {
    private RpcClientConfig rpcClientConfig;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;


    public RpcClient(RpcClientConfig rpcClientConfig) {
        this.rpcClientConfig = rpcClientConfig;
        this.encoder = ReflectionUtils.newInstance(this.rpcClientConfig.getEnCoderClass());
        this.decoder = ReflectionUtils.newInstance(this.rpcClientConfig.getDeCoderClass());
        this.transportSelector = ReflectionUtils.newInstance(this.rpcClientConfig.getSelectorClass());
        transportSelector.init(this.rpcClientConfig.getServers(), this.rpcClientConfig.getConnectCount(), this.rpcClientConfig.getTransportClass());

    }


    public <T>T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, transportSelector));
    }

    public RpcClient() {
        this(new RpcClientConfig());
    }


}
