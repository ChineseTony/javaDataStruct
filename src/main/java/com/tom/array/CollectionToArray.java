package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WangTao
 *
 * 使用toArray带参方法，数组空间大小的length：
 * 1） 等于0，动态创建与size相同的数组，性能最好。 查看ArrayList源码
 * 2） 大于0但小于size，重新创建大小等于size的数组，增加GC负担。
 * 3） 等于size，在高并发情况下，数组创建完成之后，size正在变大的情况下，负面影响与2相同。
 * 4） 大于size，空间浪费，且在size处插入null值，存在NPE隐患。√
 */
public class CollectionToArray {



    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        String[] tmp = new String[list.size()];

        String[] array = list.toArray(tmp);
        String[] t = list.toArray(new String[0]);
        System.out.println(tmp == array);
        System.out.println(tmp == t);


        Arrays.stream(tmp).forEach(System.out::println);
        Arrays.stream(array).forEach(System.out::println);
    }
}
