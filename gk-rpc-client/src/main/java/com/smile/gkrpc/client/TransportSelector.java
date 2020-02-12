package com.smile.gkrpc.client;

import com.smile.gkrpc.Peer;
import com.smile.gkrpc.transport.TransportClient;

import java.util.List;

/**
 * server选择器
 */
public interface TransportSelector {


    /**
     * 初始化selector
     * @param peers 可以链接的server端点信息
     * @param count 建立链接数量
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport和server做交互
     *
     * @return 网络client
     */
    TransportClient select();


    /**
     * 始放client
     *
     * @param client
     */
    void release(TransportClient client);


    void close();


}
