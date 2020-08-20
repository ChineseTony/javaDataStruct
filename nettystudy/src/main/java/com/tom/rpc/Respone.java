package com.tom.rpc;

import java.io.Serializable;

/**
 * @author WangTao
 */
public class Respone<T> implements Serializable {

    private String requestId;

    private String code;

    private String message;

    private T data;


}
