package com.tom.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author WangTao
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(StudentConfiguration.class)
public @interface EnableStudent {


}
