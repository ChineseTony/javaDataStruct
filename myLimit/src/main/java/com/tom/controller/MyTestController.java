package com.tom.controller;


import com.tom.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTestController {

    @GetMapping("/test")
    @Limit(name = "wangtao", key = "test", period = 100, count = 10)
    public String test(){
        return "test";

    }

}
