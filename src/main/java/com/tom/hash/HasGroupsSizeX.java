package com.tom.hash;

import sun.nio.cs.ext.MacHebrew;

import java.util.HashMap;
import java.util.Map;


/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
 */
public class HasGroupsSizeX {

    private HasGroupsSizeX(){

    }


    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 0){
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
        int[] tmp = new int[]{1,1,1,1,2,2,2,2,2,2};
//        1,1, 1,1  2,2, 2,2  2,2
        System.out.println(hasGroupsSizeX(tmp));
    }
}
