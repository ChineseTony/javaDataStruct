package com.tom.jobs;

/**
 * @author WangTao
 */
public interface Job {

    void introduce();

    enum Type {
        //yis
        DOCTOR,
        //teacher
        TEACHER
    }
}
