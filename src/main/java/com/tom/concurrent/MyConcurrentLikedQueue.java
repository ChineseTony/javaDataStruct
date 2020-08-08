package com.tom.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.stream.IntStream;

/**
 * @author WangTao
 *
 *
 */

public class MyConcurrentLikedQueue {

    private static final Logger logger = LoggerFactory
            .getLogger(MyConcurrentLikedQueue.class);
    private String item ="";
    private static final long  itemOffset;
    private static final Unsafe unsafe;
    static {
        try {
            unsafe = reflectGetUnsafe();
            Class<?> k = MyConcurrentLikedQueue.class;
            itemOffset = unsafe.objectFieldOffset
                    (k.getDeclaredField("item"));
        } catch (Exception e) {
            throw new Error(e);
        }

    }

    public final boolean casItem(String expect, String update) {
        return unsafe.compareAndSwapObject(this, itemOffset, expect, update);
    }



    public static void main(String[] args) {
        MyConcurrentLikedQueue m = new MyConcurrentLikedQueue();

        IntStream.range(0,1000).parallel().forEach(v -> {
            m.casItem("",v+"---");
        });
        logger.info("item value is {}",m.item);


    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
