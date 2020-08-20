package com.tom.rpc;

import java.io.Serializable;

/**
 * @author WangTao
 */
public class Request  implements Serializable {

    private String requestId;

    private String interfaceName;

    private String methodName;

    //请求的类
    private String clazzName;

    //请求的参数
    private Object[] parameters;

    //请求参数类型
    private Class<?>[] parameterTypes;


}
