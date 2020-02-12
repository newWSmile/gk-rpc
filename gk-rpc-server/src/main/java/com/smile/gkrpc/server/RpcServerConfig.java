package com.smile.gkrpc.server;

import com.smile.gkrpc.codec.Decoder;
import com.smile.gkrpc.codec.Encoder;
import com.smile.gkrpc.codec.JsonDecoder;
import com.smile.gkrpc.codec.JsonEncoder;
import com.smile.gkrpc.transport.HttpTransportServer;
import com.smile.gkrpc.transport.TransportServer;
import lombok.Data;

@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private int port = 3000;



}
