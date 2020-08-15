package com.tom.service;


import org.apache.commons.lang3.StringUtils;

/**
 * @author WangTao
 */
public class CustomerFormat implements StringFormat {



    public CustomerFormat() {

    }


    @Override
    public String format(String tmp) {
        return StringUtils.join(tmp,"---->StringUtils");
    }
}
