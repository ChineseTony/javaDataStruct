package com.tom;

/***
 * @author tom
 * 如果在执行finally块前出现return语句，会把在值先缓存起来，等执行完finally块后，再返回缓存起来的值。
 */
public class FinallyStudy {

    public static void main(String[] args) {
        System.out.println(diff());
        System.out.println(getPerson());
    }

    public static int diff(){
        int a = 0;
        try {
            System.out.println("a");
            return a;
        }catch (Exception e){

        }finally {
            System.out.println("fianlly");
            a=-1;
        }
        return 0;
    }

    public static Person getPerson(){
        Person person = new Person("tom");
        try {
            return person;
        }catch (Exception e){

        }finally {
//            person.name="rose";
            person = new Person("jack");
            System.out.println("fianlly");

        }
        return person;
    }

    static class Person{
        public String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "name:"+this.name;
        }
    }
}
