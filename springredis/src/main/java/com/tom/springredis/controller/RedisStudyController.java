package com.tom.springredis.controller;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@RestController
public class  RedisStudyController{
    private Logger log = LoggerFactory.getLogger(RedisStudyController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();

    private BloomFilter<Integer> bloomFilter;

    @PostConstruct
    public void init() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
        }, 0, 1, TimeUnit.SECONDS);

        //使用布隆过滤器 过滤
        bloomFilter = BloomFilter.create(Funnels.integerFunnel(),
                10000, 0.01);
        IntStream.rangeClosed(1, 10000).forEach(bloomFilter::put);
    }

    @GetMapping("right")
    public String right2(@RequestParam("id") int id) {
        String data = "";
        if (bloomFilter.mightContain(id)) {
            String key = "user" + id;
            data = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(data)) {
                data = getCityFromDb(id);
                stringRedisTemplate.opsForValue().set(key, data, 30, TimeUnit.SECONDS);
            }
        }
        return data;
    }

    private String getCityFromDb(int id) {
        atomicInteger.incrementAndGet();
        if (id > 0 && id <= 10000) {
            return "userdata";
        }
        return "";
    }
}
