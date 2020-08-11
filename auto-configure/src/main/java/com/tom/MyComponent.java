package com.tom;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author WangTao
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {

    String value() default "";
}
