package com.tom.configuration;

import com.tom.service.CustomerFormat;
import com.tom.service.DeafultFormat;
import com.tom.service.StringFormat;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author WangTao
 */

@Configuration
@ConditionalOnProperty(prefix = "stringformat", name = "enabled", havingValue = "true",
        matchIfMissing = true) // 当属性配置不存在时，同样视作匹配
@ConditionalOnResource(resources = "META-INF/spring.factories")
@ConditionalOnNotWebApplication
public class StringFormatAutoConfiguration {

    @Bean
    @ConditionalOnMissingClass(value = "org.apache.commons.lang3.StringUtils")
    public StringFormat getStringFormat(){

        return new DeafultFormat();
    }

    @Bean
    @ConditionalOnClass(name = "org.apache.commons.lang3.StringUtils")
    public StringFormat myFormat(){
        return new CustomerFormat();
    }


}
