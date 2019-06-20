package com.tom.interview;

/**
 * @author WangTao
 */
public class TryCatchFianlly {

    public static int demo5() {
        try {
            return printX();
        }
        finally {
            System.out.println("finally trumps return... sort of");
        }
    }

    private static int printX() {
        System.out.println("X");
        return 0;
    }

    public static void main(String[] args) {
        /**输出结果
         * X
         * finally trumps return... sort of
         * 0
         * 分析字节码 才能真正了解 try catch finally 执行流程
         */
        System.out.println(demo5());
    }

}
