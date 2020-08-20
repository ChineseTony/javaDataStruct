package com.tom.util;

import cn.hutool.core.collection.ConcurrentHashSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.omg.CORBA.Current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangTao
 */
public class CuratorUtil {

    private static final Logger logger = LoggerFactory.getLogger(CuratorUtil.class);

    //等待时间
    private static final int BASE_SLEEP_TIME = 1000;
    //最大重试次数
    private static final int MAX_RETRIES = 3;

    private static final Map<String, List<String>> SERVICE_ADDRESS_MAP =
            new ConcurrentHashMap<>(16);
    private static final Set<String> ALL_PATH_SET = new ConcurrentHashSet<>();
    private static final String FILE_NAME = "zk.properties";

    private static final String DEFAULT_ZOOKEEPER = "localhost:2181";

    private static CuratorFramework zkClient;

    private CuratorUtil(){

    }

    static {
        zkClient = getZkClient();
    }

    private static CuratorFramework getZkClient(){
        Properties props = new Properties();
        try(InputStream input= CuratorUtil.class.getClassLoader()
                .getResourceAsStream("zk.properties")){
//            指定编码防止乱码问题出现
            props.load(new InputStreamReader(input, StandardCharsets.UTF_8));
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

    public static void createPath(String path){
        try {
            if (ALL_PATH_SET.contains(path) || zkClient.checkExists().forPath(path) != null){
                logger.error("path{} is exists",path);
            }else {
                zkClient.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(path);
                logger.info("path{} is created",path);
                ALL_PATH_SET.add(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void closeZk(){
        if(zkClient != null){
            zkClient.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "/rpc/wangtao";
        CuratorUtil.createPath(path);
    }


}
