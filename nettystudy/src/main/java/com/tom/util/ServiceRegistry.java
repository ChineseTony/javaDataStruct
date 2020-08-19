package com.tom.util;

import java.net.InetAddress;

/**
 * @author WangTao
 */
public interface ServiceRegistry {

    /**
     *
    * @param rpcServiceName 提供方的名字
     * @param inetAddress 提供方的地址
     */
    void serviceRegistry(String rpcServiceName, InetAddress inetAddress);
}
