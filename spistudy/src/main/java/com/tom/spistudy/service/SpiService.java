package com.tom.spistudy.service;

import com.tom.spistudy.MySPI;

/**
 * @author WangTao
 */
@MySPI("chinese")
public interface SpiService {

    /**
     *
     * @param msg
     * @return
     */
    String say(String msg);
}
