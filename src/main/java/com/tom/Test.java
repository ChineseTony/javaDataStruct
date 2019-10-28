package com.tom;


/**
 * @author WangTao
 */
public class Test {
    public static void main(String[] args) {
        //负数存放的是补码
        // 原码= 符号位 不变 补码-1  |  符号位不变 各位取反
        //1000 0110  1010 0001
        int x = 0x86a1;
        //1000 1001  1010 0000
        //1111 1001  0101 1111
        short s = (short)x;

        // 1111 1001 0101 1111
        // 1 负数   111 1001 0101 1111 31071
        System.out.println(s);

    }

}
