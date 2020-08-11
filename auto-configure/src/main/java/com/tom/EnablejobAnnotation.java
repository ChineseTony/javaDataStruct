package com.tom;

import com.tom.configuration.EnableJob;
import com.tom.jobs.Job;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangTao
 */
@EnableJob(type = Job.Type.DOCTOR)
public class EnablejobAnnotation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnablejobAnnotation.class);
        context.refresh();
        Job job = context.getBean(Job.class);
        job.introduce();
        context.close();
    }
}
