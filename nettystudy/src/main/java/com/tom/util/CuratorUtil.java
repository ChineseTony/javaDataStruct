package com.tom.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangTao
 */
public class CuratorUtil {

    //等待时间
    private static final int BASE_SLEEP_TIME = 1000;
    //最大重试次数
    private static final int MAX_RETRIES = 3;

    private static final Map<String, List<String>> SERVICE_ADDRESS_MAP =
            new ConcurrentHashMap<>(16);
    private static final Set<String> ALL_PATH_SET = ConcurrentHashMap.newKeySet();
    private static final String FILE_NAME = "zk.properties";

    private static final String DEFAULT_ZOOKEEPER = "localhost:2181";

    private static CuratorFramework zkClient;

    private CuratorUtil(){

    }

    static {
        zkClient = getZkClient();
    }

    public static CuratorFramework getZkClient(){
        Properties props = new Properties();
        try(InputStream input= CuratorUtil.class.getClassLoader()
                .getResourceAsStream("zk.properties")){
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (zkClient != null && zkClient.getState() == CuratorFrameworkState.STARTED) {
            return zkClient;
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
        String zkAddress =props.get("zk.address").toString();
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(StringUtils.isEmpty(zkAddress) ? DEFAULT_ZOOKEEPER : zkAddress)
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();
        return zkClient;

    }

    public static void main(String[] args) throws Exception {
        String path = "/rpc/wangtao";
        if (zkClient.checkExists().forPath(path) != null){
            zkClient.delete().deletingChildrenIfNeeded().forPath(path);
        }
        zkClient.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath(path);
        System.out.println(zkClient.checkExists().forPath(path));
    }


}
