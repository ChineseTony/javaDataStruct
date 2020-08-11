package com.tom.jobs;

import org.springframework.stereotype.Component;

/**
 * @author WangTao
 */
@Component
public class TeacherJob implements Job {
    @Override
    public void introduce() {
        System.out.println("hello,my job is teacher");
    }
}
