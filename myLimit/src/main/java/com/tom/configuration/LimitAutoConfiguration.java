package com.tom.configuration;


import com.tom.enums.LimitType;
import com.tom.properties.LimitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableConfigurationProperties(LimitProperties.class)
@ConditionalOnBean({RedisTemplate.class, HttpServletRequest.class})
@ConditionalOnProperty(prefix = "spring.tom.limit", value = "enabled", matchIfMissing = true)
public class LimitAutoConfiguration {



    @Bean
    @ConditionalOnMissingBean(LimitProperties.class)
    // 当容器中没有指定Bean的情况下，自动配置LimitProperties类 相当于默认配置
    public LimitProperties limitService(){
        LimitProperties limitProperties = new LimitProperties();
        limitProperties.setLimitType(LimitType.CUSTOMER);
        return limitProperties;
    }




}
