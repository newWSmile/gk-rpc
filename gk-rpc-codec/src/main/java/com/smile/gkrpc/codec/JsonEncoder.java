package com.smile.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的序列化实现
 */
public class JsonEncoder implements Encoder {
    @Override
    public byte[] encoder(Object object) {
        return JSON.toJSONBytes(object);
    }
}
