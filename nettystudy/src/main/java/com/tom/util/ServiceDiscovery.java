package com.tom.util;

import java.net.InetSocketAddress;

/**
 * @author WangTao
 */
public interface ServiceDiscovery {

    InetSocketAddress lookupService(String rpcServiceName);
}
