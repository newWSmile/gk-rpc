package com.smile.gkrpc.transport;

/**
 * 1 启动监听
 * 2 接受请求
 * 3 关闭监听
 */
public interface TransportServer {

    void start();

    void init(int port, RequestHandler requestHandler);

    void stop();

}
