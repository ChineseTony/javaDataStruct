package com.tom.spistudy.myextends;

import com.tom.spistudy.service.SpiService;

/**
 * @author WangTao
 */
public class ChineseSpiService  implements SpiService {


    @Override
    public String say(String msg) {
        return "你好:"+msg;
    }
}
