package com.smile.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest {

    @Test
    public void decoder() {
        Encoder encoder = new JsonEncoder();
        TestBean testBean = new TestBean();
        testBean.setName("smile");
        testBean.setAge(24);
        byte[] bytes = encoder.encoder(testBean);
        Decoder decoder = new JsonDecoder();
        TestBean testBean1 = decoder.decoder(bytes, TestBean.class);
        assertEquals("smile",testBean1.getName());

        assertEquals(24,testBean1.getAge());
    }
}