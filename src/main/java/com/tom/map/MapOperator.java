package com.tom.map;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangtao
 * @date 2021/8/5
 */
public class MapOperator {


    static class Person{
        private String name;
        private String city;

        public Person(String name,String city) {
            this.name = name;
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Stream<Person> people = Stream
                .of(new Person("张三", "杭州")
                        , new Person("张三", "深圳")
                        , new Person("李四", "南京"));
        Map<String, String> phoneBook = people.collect
                (Collectors.toMap(Person::getName, Person::getCity
                        ,(v1,v2) ->  v1));
        for(Map.Entry<String,String> entry:phoneBook.entrySet()){
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }

    }
}
