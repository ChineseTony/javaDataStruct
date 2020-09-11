package com.tom.string;

/**
 * @author WangTao
 */
public class MystrStr {

    public int strStr(String haystack, String needle) {
        int i=0;
        int j=0;
        //标志位
        int flag=0;
        if(needle.length()==0){
            return 0;
        }
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
                flag++;
            }else{
                j=0;
//                i 跳过前面元素
                i=i-flag+1;
                flag=0;
            }

            if(j==needle.length() && flag==needle.length()){
                return i-needle.length();
            }
        }
        return -1;

    }
}
