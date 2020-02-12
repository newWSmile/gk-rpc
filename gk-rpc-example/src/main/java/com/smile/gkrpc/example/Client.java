package com.smile.gkrpc.example;

import com.smile.gkrpc.client.RpcClient;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Smile
 * Data 2020/2/12 17:48
 **/
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();

        CalcService calcService = client.getProxy(CalcService.class);

        int r1 = calcService.add(3,4);

        int r2 = calcService.minus(10,4);
        System.out.println(r1);
        System.out.println(r2);

    }

}
