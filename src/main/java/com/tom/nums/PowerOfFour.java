package com.tom.nums;

/**
 * @author WangTao
 * 你能不使用循环或者递归来完成本题吗？
 */
public class PowerOfFour {

    /**
     * 循环
     * @param num
     * @return
     */
    public static boolean isPowerOfFour1(int num) {
        if (num == 1){
            return true;
        }
        long m = 1;
        while (m < num){
            m = m * 4;
            if (m == num){
                return true;
            }
        }
        return false;
    }

    /**
     * 递归调用
     * @param num
     * @return
     */
    public boolean isPowerOfFour2(int num){
        if (num == 1){
            return true;
        }
        if (num == 0){
            return false;
        }
        return isPowerOfFour2(num/4) && num % 4 == 0;
    }

    public boolean isPowerOfFour3(int num){


        return false;
    }



    public static void main(String[] args) {
        System.out.println(isPowerOfFour1(18));
    }
}
