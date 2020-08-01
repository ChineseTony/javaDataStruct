package com.tom;

import com.tom.enums.LimitType;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limit {
    /**
     * 资源的名字
     *
     * @return String
     */

    @Value("${spring.tom.limit.name}")
    String name() default "";

    /**
     * 资源的key
     *
     * @return String
     */
    @Value("${spring.tom.limit.key}")
    String key() default "";

    /**
     * Key的prefix
     *
     * @return String
     */
    @Value("${spring.tom.limit.prefix}")
    String prefix() default "";

    /**
     * 给定的时间段
     * 单位秒
     *
     * @return int
     */
    @Value("${spring.tom.limit.period}")
    int period();

    /**
     * 最多的访问限制次数
     *
     * @return int
     */
    @Value("${spring.tom.limit.count}")
    int count();

    /**
     * 类型
     *
     * @return LimitType
     */
    @Value("${spring.tom.limit.limitType}")
    LimitType limitType() default LimitType.CUSTOMER;
}
