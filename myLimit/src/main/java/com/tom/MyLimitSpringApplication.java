package com.tom;


import com.tom.enums.LimitType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableLimit(type = LimitType.IP)
//@Profile(value = "dev")
public class MyLimitSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyLimitSpringApplication.class, args);
    }

}
