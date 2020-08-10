package com.tom.spistudy;

import java.lang.annotation.*;

/**
 * @author WangTao
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MySPI {

    /**
     * 默认拓展名
     */
    String value() default "";

}