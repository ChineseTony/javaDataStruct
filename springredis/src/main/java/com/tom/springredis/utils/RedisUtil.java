package com.tom.springredis.utils;



import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.tom.springredis.controller.RedisStudyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Collections;

@Component
public class RedisUtil implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private ApplicationContext applicationContext;

//    private  static BloomFilter<String>  bloomFilter;

    private RedisUtil(){

    }

    private static RedisTemplate<String,String> redisTemplate;

    // one soluation
//    @Autowired
//    public  void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
//        RedisUtil.redisTemplate = redisTemplate;
//    }


//    依赖查找 手动注入
    @PostConstruct
    public void init(){
        redisTemplate =(RedisTemplate<String, String>) applicationContext.getBean("stringRedisTemplate");

//        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),
//                10000, 0.01);
    }


    public static void putKey(String key, String val){
        redisTemplate.opsForValue().set(key,val, Duration.ofMinutes(1));
    }


    public static void safedUnLock(String key, String val) {
        String luaScript = "local in = ARGV[1] local curr=redis.call('get', KEYS[1]) if in==curr then redis.call('del', KEYS[1]) end return 'OK'";
        RedisScript<String> redisScript = RedisScript.of(luaScript);
        redisTemplate.execute(redisScript, Collections.singletonList(key), Collections.singleton(val));
    }

    public static boolean containsKey(String key){
        String value =  redisTemplate.opsForValue().get(key);
        log.info("value===>{}",value);
        return !StringUtils.isEmpty(value);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
