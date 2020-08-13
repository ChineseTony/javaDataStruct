package com.tom.component;

import com.tom.configuration.MyConditional;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author WangTao
 */

@Component
public class MyPropertyComponent {

    @Bean("myEnvironment")
    @MyConditional("dev")
    public String getEnvironment(){
        return "我是开发环境";
    }

    @Bean("myEnvironment")
    @MyConditional("pod")
    public String getEnvironment2(){
        return "我是生产环境";
    }
}
