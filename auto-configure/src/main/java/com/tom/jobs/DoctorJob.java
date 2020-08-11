package com.tom.jobs;

import org.springframework.stereotype.Component;

/**
 * @author WangTao
 */

@Component
public class DoctorJob implements Job {
    @Override
    public void introduce() {
        System.out.println("I am  doctor");
    }
}
