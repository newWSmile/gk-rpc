package com.smile.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest {

    @Test
    public void encoder() {
        Encoder encoder = new JsonEncoder();
        TestBean testBean = new TestBean();
        testBean.setName("smile");
        testBean.setAge(24);
        byte[] bytes = encoder.encoder(testBean);
        assertNotNull(bytes);


    }
}