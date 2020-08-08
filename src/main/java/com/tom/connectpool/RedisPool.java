package com.tom.connectpool;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.tom.util.NameThreadFactory;

import java.util.concurrent.*;

/**
 * @author WangTao
 */
public class RedisPool {
    public static final Logger logger = LoggerFactory.getLogger(RedisPool.class);

    private static final int timeout = 1000;
    private static final JedisPoolConfig config;

    public static void main(String[] args) throws Exception {
        right();

    }

    static {
        config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1000);
    }

    public static void right() throws Exception {

        ExecutorService executor = new ThreadPoolExecutor(
                10,100,1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(1000),
                new NameThreadFactory("wangtao"),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        Semaphore semaphore = new Semaphore(100);
        for (int i = 0; i < 1000; i++) {
            final int tmp = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String key = "a"+tmp;
                String result =setVal(key,""+tmp);
                if (!"ok".equalsIgnoreCase(result)) {
                    logger.warn("Expect a to be ok but found {}", result);
                }else {
                    logger.info("set {} to redis",getVal(key));
                }
                semaphore.release();
            });
        }
        executor.awaitTermination(1,TimeUnit.SECONDS);
        executor.shutdown();
    }

    private static String setVal(String key,String val){
        try (JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379,timeout);
        Jedis jedis = jedisPool.getResource()) {
             return jedis.set(key, val);
        }
    }

    private static String getVal(String key){
        try (JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379,timeout);
             Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }
}
