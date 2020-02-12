package com.smile.gkrpc;

import lombok.Data;

import java.util.Objects;

/**
 * rpc的返回
 */
@Data
public class Response {

    private int code  = 0;

    private String message = "ok";

    private Object data;

}
