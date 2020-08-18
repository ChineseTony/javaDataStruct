package com.tom.spistudy.proxy.propetype;

import java.util.ArrayList;


/**
 * @author WangTao
 */
public class ListClone {

    public static void main(String[] args) {
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(130);
        ArrayList<Integer> arr = (ArrayList<Integer>) (tmp).clone();
        System.out.println(tmp == arr);
        System.out.println(arr.get(0) == tmp.get(0));


    }
}
