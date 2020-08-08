package com.tom.equalsstudy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author WangTao
 *
 * equals 和 hashCode 必须同时重写
 * 如果两个对象的hashCode相同，它们并不一定相同。
 */
public class EqualsStudy {
    private static final Logger logger = LoggerFactory.getLogger(EqualsStudy.class);

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        Student two = new Student(2, "wang");
        list.add(new Student(1, "zhang"));
        list.add(two);
        Student student = new Student(2, "li");
        int index = Collections.binarySearch(list,student);
        if (index == -1){
            logger.info("student:{},not found",student);
        }else {
            logger.info("student:{} in index:{}",student,index);
        }
        Set<Student> my = new HashSet<> ();
        my.add(student);
        my.add(two);
        logger.info("student equasl tow? {}",student.equals(two));
        my.stream().forEach(
                v -> {
                    logger.info("student:{} in set",v);
                }
        );


    }

    static class Student implements Comparable<Student> {

        private int age;
        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        // 重写 equals 和 hashCode
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
//            return age == student.age &&
////                  Objects.equals(name, student.name);

            return age == student.age;
        }

        @Override
        public int hashCode() {
//            return Objects.hash(age, name);
            return Objects.hash(age);

        }

        @Override
        public int compareTo(Student o) {
            return Comparator.comparing(Student::getName)
                    .thenComparingInt(Student::getAge)
                    .compare(this, o);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
