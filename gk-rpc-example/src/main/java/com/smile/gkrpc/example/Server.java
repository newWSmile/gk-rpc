package com.smile.gkrpc.example;

import com.smile.gkrpc.server.RpcServer;

/**
 * @ClassName Server
 * @Description TODO
 * @Author Smile
 * Data 2020/2/12 17:52
 **/
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();

        server.register(CalcService.class, new CalcServiceImpl());

        server.start();

    }

}
