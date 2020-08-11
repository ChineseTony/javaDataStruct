package com.tom.configuration;


import com.tom.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangTao
 */
@Configuration
public class StudentConfiguration {

    @Bean
    public Student student(){
        return new Student("tom","school");
    }


}
