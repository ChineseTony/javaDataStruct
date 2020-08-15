package com.tom.service;

/**
 * @author WangTao
 */
public class DeafultFormat implements StringFormat {


    @Override
    public String format(String tmp) {
        return "默认format---->"+tmp;
    }
}
