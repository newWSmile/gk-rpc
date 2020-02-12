package com.smile.gkrpc.server;

import com.smile.gkrpc.Request;
import com.smile.gkrpc.Response;
import com.smile.gkrpc.codec.Decoder;
import com.smile.gkrpc.codec.Encoder;
import com.smile.gkrpc.common.utils.ReflectionUtils;
import com.smile.gkrpc.transport.RequestHandler;
import com.smile.gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {

    private RpcServerConfig config;

    private TransportServer net;

    private Encoder encoder;

    private Decoder decoder;


    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public RpcServer(){
        this(new RpcServerConfig());
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decoder(inBytes, Request.class);
                log.info("get request is :{}", request);
                ServiceInstance serviceInstance = serviceManager.lookUp(request);
                Object invoke = serviceInvoker.invoke(serviceInstance, request);
                response.setData(invoke);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("RpcServer got error : " + e.getClass().getName() + " : " + e.getMessage());
            } finally {
                byte[] encoderBytes = RpcServer.this.encoder.encoder(response);
                try {
                    toResp.write(encoderBytes);
                    log.info("response client ");
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }

        }
    };

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }
}
