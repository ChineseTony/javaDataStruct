package com.tom.interview;

/**
 * @author WangTao
 */

public class TryCatchFinally2 {

    public static void main(String[] args) {
        System.out.println(increment());

    }

    public static int increment(){
        int i = 0;
        try {
            i++;
            return i;
        }catch (Exception e){
            return  i;
        }finally {
            System.out.println("function---->"+i);
            i++;
        }
    }
}
