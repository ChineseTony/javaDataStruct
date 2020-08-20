package com.tom.util;


import java.net.InetSocketAddress;

/**
 * @author WangTao
 */
public interface ServiceRegistry {

    /**
     *
    * @param rpcServiceName 提供方的名字
     * @param inetSocketAddress 提供方的地址
     */
    void serviceRegistry(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
