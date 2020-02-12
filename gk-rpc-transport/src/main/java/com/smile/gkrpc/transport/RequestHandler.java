package com.smile.gkrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的hanlder
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream toResp);

}
