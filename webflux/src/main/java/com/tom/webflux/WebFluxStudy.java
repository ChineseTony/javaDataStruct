package com.tom.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author WangTao
 */

@SpringBootApplication
@EnableAsync
@ServletComponentScan(basePackages  = "com.tom.webflux.servlet")
@EnableReactiveMongoRepositories
public class WebFluxStudy{

    public static void main(String[] args) {
        SpringApplication.run(WebFluxStudy.class,args);
    }
}
