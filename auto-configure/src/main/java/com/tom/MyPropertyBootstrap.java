package com.tom;

import com.tom.component.MyPropertyComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author WangTao
 */

public class MyPropertyBootstrap {

    static {
        System.setProperty("spring.profiles.active","pod");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles("dev");
        applicationContext.register(MyPropertyComponent.class);
        applicationContext.refresh();
        String myEnvironment = applicationContext.getBean("myEnvironment",String.class);
        System.out.println(myEnvironment);
        applicationContext.close();
    }
}
