package com.tom.equalsstudy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author WangTao
 *   -XX:+PrintStringTableStatistics
 *   -XX:StringTableSize=10000
 *   -XX:+PrintGC
 *  -XX:+PrintGCDetails
 */
public class StringEquals {

    private static final Logger logger = LoggerFactory.getLogger(StringEquals.class);
    private static final int TIMES = 100000 ;

    public static void main(String[] args) {

        stringCompare2();
        intCompare();
    }


//    -XX:AutoBoxCacheMax=<size>
    private static void intCompare(){
        Integer a = 127; //Integer.valueOf(127)
        Integer b = 127; //Integer.valueOf(127)
        logger.info("\nInteger a = 127;\n" +
                "Integer b = 127;\n" +
                "a == b ? {}", a == b);    // true

        Integer c = 128; //Integer.valueOf(128)
        Integer d = 128; //Integer.valueOf(128)
        logger.info("\nInteger c = 128;\n" +
                "Integer d = 128;\n" +
                "c == d ? {}", c == d);   //false
        //设置-XX:AutoBoxCacheMax=1000再试试

        Integer e = 127; //Integer.valueOf(127)
        Integer f = new Integer(127); //new instance
        logger.info("\nInteger e = 127;\n" +
                "Integer f = new Integer(127);\n" +
                "e == f ? {}", e == f);   //false

        Integer g = new Integer(127); //new instance
        Integer h = new Integer(127); //new instance
        logger.info("\nInteger g = new Integer(127);\n" +
                "Integer h = new Integer(127);\n" +
                "g == h ? {}", g == h);  //false

        Integer i = 128; //unbox
        int j = 128;
        logger.info("\nInteger i = 128;\n" +
                "int j = 128;\n" +
                "i == j ? {}", i == j); //true
    }

    private static void stringCompare2(){
        List<String> stringList= IntStream.range(1,TIMES)
        .mapToObj(i -> String.valueOf(i).intern())
                .collect(Collectors.toList());

        logger.info("list size:{}",stringList.size());
    }


    private static void stringCompare(){
        String a = "1";
        String b = "1";
        logger.info("a == b?{}",a == b);
        String c = new String("1");
        String d = new String("1");
        logger.info("c == d?{}",c == d);
        String e = new String("1").intern();
        String f = new String("1").intern();
        logger.info("e == f?{}",e == f);
        String g = new String("1");
        String h = new String("1");
        logger.info("e == f?{}",g.equals(h));
    }
}
