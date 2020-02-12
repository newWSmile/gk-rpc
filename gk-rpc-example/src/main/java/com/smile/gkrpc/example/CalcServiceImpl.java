package com.smile.gkrpc.example;

/**
 * @ClassName CalcServiceImpl
 * @Description TODO
 * @Author Smile
 * Data 2020/2/12 17:47
 **/
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
