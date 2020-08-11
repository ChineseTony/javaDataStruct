package com.tom.configuration;

import com.tom.JobSelector;
import com.tom.jobs.Job;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author WangTao
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JobSelector.class)
public @interface EnableJob {


    Job.Type type();
}
