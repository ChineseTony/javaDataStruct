package com.tom.util.zk;

import com.tom.util.CuratorUtil;
import com.tom.util.ServiceRegistry;


import java.net.InetSocketAddress;

/**
 * @author WangTao
 */
public class ZkServiceRegistry implements ServiceRegistry {




    @Override
    public void serviceRegistry(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String path = rpcServiceName+"/"+inetSocketAddress.toString();
        CuratorUtil.createPath(path);
    }
}
