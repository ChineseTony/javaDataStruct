package com.tom.array;


/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/climbing-stairs/
 */
public class ClimbStairs {


    public static int climbStairs(int n) {
        if(n <= 1){
            return 1;
        }

        return climbStairs(n-1) + climbStairs(n-2);

    }

    public int climbStairs2(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    public static void main(String[] args) {
        System.out.println(climbStairs(2));

    }
}
