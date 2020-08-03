package com.tom.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author WangTao
 */
public class CollectorsToMapDemo {

    private static final Logger logger = LoggerFactory.getLogger(CollectorsToMapDemo.class);

    public static void main(String[] args) {
        List<Pair<String,Double>> pairArrayList = new ArrayList<>(3);

        pairArrayList.add(new Pair<>("version", 12.10));
        pairArrayList.add(new Pair<>("version", 12.19));
        pairArrayList.add(new Pair<>("version", 6.28));

        //重复key报错  需要添加重复key处理(o1,o2) -> o2
        Map<String, Double> map = pairArrayList.
                stream().filter(Objects::nonNull).collect(
                Collectors.toMap(Pair::getKey,Pair::getValue
                , (o1,o2) -> o2)
        );
        map.forEach((k,v) ->
            logger.info("key:{}---->value:{}",k,v)
        );
    }
}
