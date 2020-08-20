package com.tom.util;

import java.net.InetSocketAddress;

/**
 * @author WangTao
 */
public interface ServiceDiscovery {


    /**
     *
     * @param rpcServiceName
     * @return
     */
    InetSocketAddress lookupService(String rpcServiceName);
}
