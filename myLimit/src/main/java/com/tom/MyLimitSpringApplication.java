package com.tom;


import com.tom.enums.LimitType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableLimit(type = LimitType.IP)
@EnableWebMvc
public class MyLimitSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyLimitSpringApplication.class, args);
    }

}
