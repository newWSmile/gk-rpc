package com.smile.gkrpc.client;

import com.smile.gkrpc.Request;
import com.smile.gkrpc.Response;
import com.smile.gkrpc.ServiceDescriptor;
import com.smile.gkrpc.codec.Decoder;
import com.smile.gkrpc.codec.Encoder;
import com.smile.gkrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName RemoteInvoker
 * @Description 调用远程服务的代理类
 * @Author Smile
 * Data 2020/2/12 17:30
 **/
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;

    private RpcClientConfig rpcClientConfig;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector transportSelector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();

        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("failed to invoke remote:" + response);
        }

        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient transportClient = null;
        Response response = null;

        try {
            transportClient = transportSelector.select();

            byte[] encoderBytes = this.encoder.encoder(request);

            InputStream receive = transportClient.write(new ByteArrayInputStream(encoderBytes));

            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            response = this.decoder.decoder(inBytes, Response.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error:" + e.getClass() + " : " + e.getMessage());


        } finally {

            if (transportClient != null) {
                transportSelector.release(transportClient);
            }
        }
        return response;
    }
}
