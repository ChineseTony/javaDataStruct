package com.tom.springredis.utils;



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
import java.time.Duration;
import java.util.Collections;

@Component
public class RedisUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

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
        System.out.println("value===>"+value);
        return !StringUtils.isEmpty(value);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
