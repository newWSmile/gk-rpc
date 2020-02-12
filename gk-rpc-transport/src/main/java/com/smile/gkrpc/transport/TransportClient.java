package com.smile.gkrpc.transport;

import com.smile.gkrpc.Peer;

import java.io.InputStream;

/**
 * 1 创建连接
 * 2 发送数据 等待相应
 * 3 关闭链接
 */
public interface TransportClient {


    void connect(Peer peer);

    InputStream write(InputStream data);


    void close();

}
