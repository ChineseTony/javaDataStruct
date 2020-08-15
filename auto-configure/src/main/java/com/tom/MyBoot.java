package com.tom;


import com.tom.service.StringFormat;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * @author WangTao
 */

@SpringBootApplication
public class MyBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(MyBoot.class)
                        .web(WebApplicationType.NONE)
                 .properties("stringformat.enabled=true").run(args);

        StringFormat stringFormat = context.getBean(StringFormat.class);

        System.out.println(stringFormat.format("tom"));
        context.close();

    }


}
