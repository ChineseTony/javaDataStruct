package com.tom.controllerAdvice;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> errorHandler(Exception ex) {
        Map<String,Object> map = new HashMap<>();
        map.put("code", 500);
        //判断异常的类型,返回不一样的返回值
        if(ex instanceof RuntimeException){
            map.put("msg","系统错误："+ ex.getMessage());
        }
        return  map;
    }

}
