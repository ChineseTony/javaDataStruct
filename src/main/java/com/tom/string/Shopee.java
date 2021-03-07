package com.tom.string;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tom
 */
public class Shopee {

    /**
     * 解压字符串
     *  b2*[a3*[bc]] -->   babcbcbcabcbcbc   数字重复的次数 *遇到压缩字符串
     * @param s
     * @return
     *
     * a*2[bc]  ->递归
     * 任务分解 大问题 --->小问题
     */
    public static String getCompress(String s){
        if (s == null || s.length() == 0){
            return s;
        }
        // *2[bc]
        StringBuilder times = new StringBuilder();
        String next = "";
        StringBuilder prev = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                next = s.substring(i+2,s.length()-1);
                break;
            }
            if (Character.isDigit(s.charAt(i))) {
                times.append(s.charAt(i));
            }else {
                prev.append(s.charAt(i));
            }

        }
        //没有重复的字符串数字
        if (times.length() == 0){
            return s;
        }
        //获取解析子问题递归调用
        String body = getCompress(next);
        StringBuilder sb = new StringBuilder();
        int timesValue = Integer.parseInt(times.toString());
        for (int i = 0; i < timesValue; i++) {
            sb.append(body);
        }
        return prev + sb.toString();
    }


    public String destCity(List<List<String>> paths) {
        String result = "";
        Map<String,Integer> map = new HashMap<>(16);
        Set<String> end = new HashSet<>();
        for (List<String> path:paths){
            String string = path.get(0);
            String tmp = path.get(1);
            map.put(string,map.getOrDefault(string,0)+1);;
            map.put(tmp,map.getOrDefault(tmp,0)+1);;
            end.add(tmp);
        }
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            if (entry.getValue() == 1 && end.contains(entry.getKey())){
                return entry.getKey();
            }
        }
        return result;
    }


    public String destCity2(List<List<String>> paths) {
        HashMap<String,String> hashMap = new HashMap<>();
        for (List<String> path : paths) {
            hashMap.put(path.get(0),path.get(1));
        }
        String key = paths.get(0).get(0);
        while(hashMap.containsKey(key)) {
            key = hashMap.get(key);
        }
        return key;
    }




    public static void main(String[] args) {
        System.out.println(getCompress("a2*[bc]"));
        System.out.println(getCompress("e3*[fa10*[c]]"));

    }
}
