package com.tom.configuration;

/**
 * @author WangTao
 */


import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD}) // 只能标注在方法上面
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MyPropertyCondition.class)
public @interface MyConditional {

    @AliasFor("environment")
    String value() default "";

    @AliasFor("value")
    String environment()  default "";



}
