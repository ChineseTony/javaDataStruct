package com.tom.interview;




import cn.hutool.core.util.BooleanUtil;

import java.util.Collection;

/**
 * @author WangTao
 * 第一阶段，选择了函数1。
 *
 * 第二阶段，允许自动装箱和拆箱，但是仍然不匹配可变参数的函数，仍然无法确认使用哪个 and函数，因为自动装箱仍然没有找到 3 个 boolean 参数的 and 函数。
 *
 * 第三阶段，允许自动装箱和拆箱，允许匹配变长参数。
 *
 * 问题就出现在第三个阶段，允许匹配变长参数时就要允许自动拆箱和装箱，这样函数 3 和函数 4 都可匹配到，因此无法通过编译。
 */
public class BooleanDemo {

    public static void main(String[] args) {
        boolean result = and(true, true, true);
        System.out.println(result);
        justPrint(true);
    }

    // 函数1
    private static void justPrint(boolean b) {
        System.out.println(b);
    }

    // 函数2
    private static void justPrint(Boolean b) {
        System.out.println(b);
    }

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

    // 函数4 mbiguous method call
//    private static boolean and(Boolean... booleans) {
//        System.out.println("Boolean");
//        for (Boolean b : booleans) {
//            if (!b) {
//                return false;
//            }
//        }
//        return true;
//    }


    public static Boolean and(Collection<Boolean> booleans){
        return BooleanUtil.andOfWrap(booleans.toArray(new Boolean[booleans.size()]));
    }


}
