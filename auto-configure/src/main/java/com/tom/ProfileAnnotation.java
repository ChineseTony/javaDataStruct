package com.tom;

import com.tom.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author WangTao
 * -Dspring.profiles.active=pro
 */
public class ProfileAnnotation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext();
        context.register(ProfileAnnotation.class);
        ConfigurableEnvironment environment = context.getEnvironment();
//        environment.setActiveProfiles("pro");
//        environment.setDefaultProfiles("dev");
        context.refresh();
        Student student = context.getBean(Student.class);
        System.out.println(student);
        context.close();
    }

    @Bean
    @Profile("pro")
    public Student student(){
        return new Student("pro","school");
    }

    @Bean
    @Profile("dev")
    public Student student2(){
        return new Student("dev","school");
    }

}
