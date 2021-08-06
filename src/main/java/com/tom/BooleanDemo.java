package com.tom;

import org.apache.commons.lang3.BooleanUtils;

/**
 * @author wangtao
 * @date 2021/8/4
 */
public class BooleanDemo {

    public static void main(String[] args) {
        boolean result = and(new boolean[]{true, true, true});
        System.out.println(result);
        justPrint(true);

    }

    // 函数1
    private static void justPrint(boolean b) {
        System.out.println(b);
    }

//    // 函数2
//    private static void justPrint(Boolean b) {
//        System.out.println(b);
//    }

    // 函数3
    private static boolean and(boolean... booleans) {
        System.out.println("boolean");
        for (boolean b : booleans) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    // 函数4
    private static Boolean and(Boolean... booleans) {
        System.out.println("Boolean");
        for (Boolean b : booleans) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
