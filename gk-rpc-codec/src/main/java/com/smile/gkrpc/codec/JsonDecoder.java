package com.smile.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基础json的发序列化
 */
public class JsonDecoder implements Decoder {
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
