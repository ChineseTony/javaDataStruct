package com.tom.spistudy.myextends;

import com.tom.spistudy.service.SpiService;

/**
 * @author WangTao
 */
public class EnglishService implements SpiService {
    @Override
    public String say(String msg) {
        return "hello:"+msg;
    }
}
