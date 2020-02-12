package com.smile.gkrpc.codec;

/**
 * 序列化
 */
public interface Encoder {

    byte[] encoder(Object object);

}
