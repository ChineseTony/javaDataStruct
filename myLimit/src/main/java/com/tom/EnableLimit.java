package com.tom;


import com.tom.enums.LimitType;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;


import java.lang.annotation.*;


/**
 * @author WangTao
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LimitConfigurationSelector.class)
public @interface EnableLimit {

    @NonNull
    LimitType type() default LimitType.CUSTOMER;



}
