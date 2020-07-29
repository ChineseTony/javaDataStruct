package com.tom.springredis.controller;


import com.tom.springredis.utils.RedisUtil;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public boolean test(){

        RedisUtil.putKey("wangtao","test");

        return RedisUtil.containsKey("wangtao");
    }
}
