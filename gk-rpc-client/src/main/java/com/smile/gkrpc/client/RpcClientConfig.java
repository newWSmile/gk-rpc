package com.smile.gkrpc.client;

import com.smile.gkrpc.Peer;
import com.smile.gkrpc.codec.Decoder;
import com.smile.gkrpc.codec.Encoder;
import com.smile.gkrpc.codec.JsonDecoder;
import com.smile.gkrpc.codec.JsonEncoder;
import com.smile.gkrpc.transport.HttpTransportClient;
import com.smile.gkrpc.transport.TransportClient;
import lombok.Data;

import java.util.Collections;
import java.util.List;
/**
 * @ClassName RpcClientConfig
 * @Description RpcClient 的配置信息
 * @Author Smile
 * Data 2020/2/12 17:13
 **/
@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;

    private Class<? extends Encoder> enCoderClass = JsonEncoder.class;

    private Class<? extends Decoder> deCoderClass = JsonDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<Peer> servers = Collections.singletonList(new Peer("127.0.0.1", 3000));


}
