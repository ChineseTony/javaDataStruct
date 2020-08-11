package com.tom;

import com.tom.component.MyFirstComponent;
import com.tom.configuration.EnableStudent;
import com.tom.entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author WangTao
 */

@EnableStudent
public class HierarchicalComponentAnnotation {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("META-INF/spring-context.xml");
        MyFirstComponent myFirstComponent =
                applicationContext.getBean("myFirstComponent",MyFirstComponent.class);
//        System.out.println(myFirstComponent);


        Student student = applicationContext.getBean("student",Student.class);
        System.out.println(student);
        applicationContext.close();

    }
}
